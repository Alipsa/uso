import groovy.ant.AntBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.RecordedRequest
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.apache.tools.ant.BuildException

import static org.junit.jupiter.api.Assertions.*

class CentralUploadApiTest {
  static MockWebServer server

  @BeforeAll
  static void startServer() {
    server = new MockWebServer()
    server.setDispatcher(new Dispatcher() {
      @Override
      MockResponse dispatch(RecordedRequest r) throws InterruptedException {
        def path = r.requestUrl.encodedPath()
        if (path.endsWith("/api/v1/publisher/upload")) {
          return new MockResponse().setResponseCode(200).setBody("1234567")
        }
        if (path.endsWith("/api/v1/publisher/status")) {
          return new MockResponse().setResponseCode(200)
              .setBody('{"deploymentState":"PUBLISHING"}')
              .addHeader("Content-Type", "application/json")
        }
        return new MockResponse().setResponseCode(404).setBody("no route for ${path}")
      }
    })
    server.start()
  }

  @AfterAll
  static void stopServer() {
    server.shutdown()
  }

  @Test
  void testCentralPortalClientUpload_thenStatus() {
    def base = server.url("/api/v1/").toString().replaceAll('/$', '')

    File bundleFile = new File("src/test/resources/test-project-1.0.0-bundle.zip")
    bundleFile.parentFile.mkdirs()
    bundleFile.text = "This is a test zip file"

    String expectedBearer = "Bearer " + Base64.encoder.encodeToString("user:password".bytes)

    new AntBuilder().with {
      taskdef(name: 'publishToCentral', classname: 'se.alipsa.uso.tasks.PublishToCentral')
      publishToCentral(
          username: "user",
          password: "password",
          publishUrl: base,
          bundleFile: bundleFile,
          autoDeploy: true,
          pollDelayMs: 0L
      )
    }

    // capture both requests (order irrelevant with dispatcher)
    def reqA = server.takeRequest()
    def reqB = server.takeRequest()
    def reqs = [reqA, reqB]
    def uploadReq = reqs.find { it.requestUrl.encodedPath().endsWith("/api/v1/publisher/upload") }
    def statusReq = reqs.find { it.requestUrl.encodedPath().endsWith("/api/v1/publisher/status") }

    assertNotNull(uploadReq)
    assertNotNull(statusReq)

    // upload
    assertEquals("POST", uploadReq.method)
    assertEquals("AUTOMATIC", uploadReq.requestUrl.queryParameter("publishingType"))
    assertEquals(bundleFile.name, uploadReq.requestUrl.queryParameter("name"))
    assertEquals(expectedBearer, uploadReq.getHeader("Authorization"))

    // status
    assertEquals("POST", statusReq.method)
    assertEquals(expectedBearer, statusReq.getHeader("Authorization"))
    assertEquals("1234567", statusReq.requestUrl.queryParameter("id"))
  }

  @Test
  void testCentralPortalClientUpload_status500() {
    server.setDispatcher(new Dispatcher() {
      @Override
      MockResponse dispatch(RecordedRequest r) {
        def path = r.requestUrl.encodedPath()
        if (path.endsWith("/api/v1/publisher/upload")) {
          return new MockResponse().setResponseCode(200).setBody("1234567")
        }
        if (path.endsWith("/api/v1/publisher/status")) {
          return new MockResponse().setResponseCode(500).setBody("boom")
        }
        return new MockResponse().setResponseCode(404)
      }
    })

    def base = server.url("/api/v1/").toString().replaceAll('/$', '')
    File bundleFile = new File("src/test/resources/test-project-1.0.0-bundle.zip")
    bundleFile.parentFile.mkdirs()
    bundleFile.text = "zip"

    def ant = new AntBuilder()
    def thrown = assertThrows(BuildException) {
      ant.with {
        taskdef(name: 'publishToCentral', classname: 'se.alipsa.uso.tasks.PublishToCentral')
        publishToCentral(
            username: "user",
            password: "password",
            publishUrl: base,
            bundleFile: bundleFile,
            autoDeploy: true,
            pollDelayMs: 0L
        )
      }
    }
    assertTrue((thrown.message =~ /(HTTP 500|Failed to check status|Failed to upload)/).find(),
        "Unexpected error message: ${thrown.message}")
  }
}

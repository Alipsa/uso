# setproxy

The `setproxy` task configures Java's HTTP, FTP, and SOCKS proxies for the current JVM by setting standard system properties. It allows you to enable, disable, or adjust proxy settings on the fly within your build.

## Usage Examples

```groovy
// No-op: clears HTTP and SOCKS proxy settings
target('clearProxy') {
  setproxy()
}

// Set HTTP/FTP proxy to firewall:80
target('useHttpProxy') {
  setproxy(
    proxyhost: 'firewall',
    proxyport: 80
  )
}

// Disable HTTP proxy only (leave SOCKS unchanged)
target('disableHttpProxy') {
  setproxy(proxyhost: '')
}

// Enable SOCKS proxy at socksy:1080
target('useSocksProxy') {
  setproxy(
    socksproxyhost: 'socksy',
    socksproxyport: 1080
  )
}

// Bypass proxy for local and internal hosts
target('bypassHosts') {
  setproxy(nonproxyhosts: 'localhost|*.internal.company.com')
}

// Set HTTP proxy with authentication
target('authProxy') {
  setproxy(
    proxyhost: 'proxy.example.com',
    proxyport: 8080,
    proxyuser: 'user',
    proxypassword: 'secret'
  )
}
```

## Attributes

|        Attribute | Description                                                            | Required                                                  |
|-----------------:|------------------------------------------------------------------------|-----------------------------------------------------------|
|      `proxyhost` | HTTP/FTP proxy hostname. Set to `""` to disable HTTP/FTP proxy.        | No                                                        |
|      `proxyport` | HTTP/FTP proxy port number (default: `80`).                            | No                                                        |
|      `proxyuser` | Username for HTTP/FTP proxy authentication (requires `proxypassword`). | No                                                        |
|  `proxypassword` | Password for HTTP/FTP proxy authentication.                            | No                                                        |
| `socksproxyhost` | SOCKS proxy hostname. Set to `""` to disable SOCKS proxy.              | No                                                        |
| `socksproxyport` | SOCKS proxy port number (default: `1080`).                             | No                                                        |
|  `nonproxyhosts` | Hosts to bypass proxy, separated by `                                  | ` (vertical bar); applies to HTTP (and FTP on Java 1.4+). | No       |

## Notes

- An empty string for `proxyhost` or `socksproxyhost` disables the corresponding proxy without affecting other settings.
- The `nonproxyhosts` pattern uses Java URI matching; common use is `localhost|*.domain.com`.
- Proxy settings take effect immediately for any HTTP, FTP, or SOCKS connections made after the task runs.
- Use this task to adjust proxy behavior dynamically without altering global system properties.

## Reference

- [Ant Manual: setproxy Task](https://ant.apache.org/manual/Tasks/setproxy.html)

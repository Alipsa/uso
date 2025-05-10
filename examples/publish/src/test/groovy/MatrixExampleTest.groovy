package src.test.groovy

import org.junit.jupiter.api.Test
import se.alipsa.uso.examples.dependencies.MatrixExample

class MatrixExampleTest {

  @Test
  void testMatrixExample() {
    def matrix = new MatrixExample().getMatrix()

    def result = matrix.collect { row ->
      row.collect { it * 2 }
    }

    assert result == [
        [2, 8, 14],
        [4, 10, 16],
        [6, 12, 18]
    ]

    println "Matrix example test passed!"
  }
}

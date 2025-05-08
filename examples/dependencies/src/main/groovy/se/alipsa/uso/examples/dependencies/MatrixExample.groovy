package se.alipsa.uso.examples.dependencies

import se.alipsa.matrix.core.*

class MatrixExample {

  Matrix getMatrix() {
    Matrix.builder().data([
      c1: [1, 2, 3],
      c2: [4, 5, 6],
      c3: [7, 8, 9]
    ]).build()
  }

}

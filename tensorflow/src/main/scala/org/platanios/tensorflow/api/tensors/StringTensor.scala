package org.platanios.tensorflow.api.tensors

import java.nio.ByteBuffer

import org.platanios.tensorflow.api.Exception.InvalidDataTypeException
import org.platanios.tensorflow.api._

/**
  * @author Emmanouil Antonios Platanios
  */
class StringTensor private[tensors] (
    override val shape: Shape, override val buffer: ByteBuffer,
    override val order: Order = DEFAULT_TENSOR_MEMORY_STRUCTURE_ORDER)
    extends Tensor {
  override val dataType: DataType = TFString

  override private[api] def setElementAtFlattenedIndex[T](
      index: Int, value: T)(implicit evidence: SupportedType[T]): this.type = ???

  override private[api] def getElementAtFlattenedIndex(index: Int): dataType.ScalaType = {
    val offset = TFInt64.byteSize * numElements + TFInt64.getElementFromBuffer(buffer, index * TFInt64.byteSize).toInt
    dataType.getElementFromBuffer(buffer, offset)
  }

  override def fill[T](value: T)(implicit evidence: SupportedType[T]): this.type = ???

  override private[tensors] def newTensor(shape: Shape): Tensor = ???

  override def asNumeric: NumericTensor = {
    throw InvalidDataTypeException(s"Data type '$dataType' of this tensor is not numeric.")
  }

  override def asRealNumeric: RealNumericTensor = {
    throw InvalidDataTypeException(s"Data type '$dataType' of this tensor is not real numeric.")
  }
}

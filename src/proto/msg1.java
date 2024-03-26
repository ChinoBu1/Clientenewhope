// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: msg.proto

// Protobuf Java Version: 4.26.0
package proto;

/**
 * Protobuf type {@code msg.msg1}
 */
public final class msg1 extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:msg.msg1)
    msg1OrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 0,
      /* suffix= */ "",
      msg1.class.getName());
  }
  // Use msg1.newBuilder() to construct.
  private msg1(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private msg1() {
    coefs_ = emptyLongList();
    seed_ = com.google.protobuf.ByteString.EMPTY;
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return proto.msgproto.internal_static_msg_msg1_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return proto.msgproto.internal_static_msg_msg1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            proto.msg1.class, proto.msg1.Builder.class);
  }

  public static final int COEFS_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private com.google.protobuf.Internal.LongList coefs_ =
      emptyLongList();
  /**
   * <code>repeated int64 coefs = 1;</code>
   * @return A list containing the coefs.
   */
  @java.lang.Override
  public java.util.List<java.lang.Long>
      getCoefsList() {
    return coefs_;
  }
  /**
   * <code>repeated int64 coefs = 1;</code>
   * @return The count of coefs.
   */
  public int getCoefsCount() {
    return coefs_.size();
  }
  /**
   * <code>repeated int64 coefs = 1;</code>
   * @param index The index of the element to return.
   * @return The coefs at the given index.
   */
  public long getCoefs(int index) {
    return coefs_.getLong(index);
  }
  private int coefsMemoizedSerializedSize = -1;

  public static final int SEED_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString seed_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes seed = 2;</code>
   * @return The seed.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSeed() {
    return seed_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (getCoefsList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(coefsMemoizedSerializedSize);
    }
    for (int i = 0; i < coefs_.size(); i++) {
      output.writeInt64NoTag(coefs_.getLong(i));
    }
    if (!seed_.isEmpty()) {
      output.writeBytes(2, seed_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < coefs_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(coefs_.getLong(i));
      }
      size += dataSize;
      if (!getCoefsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      coefsMemoizedSerializedSize = dataSize;
    }
    if (!seed_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, seed_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof proto.msg1)) {
      return super.equals(obj);
    }
    proto.msg1 other = (proto.msg1) obj;

    if (!getCoefsList()
        .equals(other.getCoefsList())) return false;
    if (!getSeed()
        .equals(other.getSeed())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getCoefsCount() > 0) {
      hash = (37 * hash) + COEFS_FIELD_NUMBER;
      hash = (53 * hash) + getCoefsList().hashCode();
    }
    hash = (37 * hash) + SEED_FIELD_NUMBER;
    hash = (53 * hash) + getSeed().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static proto.msg1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.msg1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.msg1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.msg1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.msg1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static proto.msg1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static proto.msg1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static proto.msg1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static proto.msg1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static proto.msg1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static proto.msg1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static proto.msg1 parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(proto.msg1 prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code msg.msg1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:msg.msg1)
      proto.msg1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return proto.msgproto.internal_static_msg_msg1_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return proto.msgproto.internal_static_msg_msg1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              proto.msg1.class, proto.msg1.Builder.class);
    }

    // Construct using proto.msg1.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      coefs_ = emptyLongList();
      seed_ = com.google.protobuf.ByteString.EMPTY;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return proto.msgproto.internal_static_msg_msg1_descriptor;
    }

    @java.lang.Override
    public proto.msg1 getDefaultInstanceForType() {
      return proto.msg1.getDefaultInstance();
    }

    @java.lang.Override
    public proto.msg1 build() {
      proto.msg1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public proto.msg1 buildPartial() {
      proto.msg1 result = new proto.msg1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(proto.msg1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        coefs_.makeImmutable();
        result.coefs_ = coefs_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.seed_ = seed_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof proto.msg1) {
        return mergeFrom((proto.msg1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(proto.msg1 other) {
      if (other == proto.msg1.getDefaultInstance()) return this;
      if (!other.coefs_.isEmpty()) {
        if (coefs_.isEmpty()) {
          coefs_ = other.coefs_;
          coefs_.makeImmutable();
          bitField0_ |= 0x00000001;
        } else {
          ensureCoefsIsMutable();
          coefs_.addAll(other.coefs_);
        }
        onChanged();
      }
      if (other.getSeed() != com.google.protobuf.ByteString.EMPTY) {
        setSeed(other.getSeed());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              long v = input.readInt64();
              ensureCoefsIsMutable();
              coefs_.addLong(v);
              break;
            } // case 8
            case 10: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              ensureCoefsIsMutable();
              while (input.getBytesUntilLimit() > 0) {
                coefs_.addLong(input.readInt64());
              }
              input.popLimit(limit);
              break;
            } // case 10
            case 18: {
              seed_ = input.readBytes();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.Internal.LongList coefs_ = emptyLongList();
    private void ensureCoefsIsMutable() {
      if (!coefs_.isModifiable()) {
        coefs_ = makeMutableCopy(coefs_);
      }
      bitField0_ |= 0x00000001;
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @return A list containing the coefs.
     */
    public java.util.List<java.lang.Long>
        getCoefsList() {
      coefs_.makeImmutable();
      return coefs_;
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @return The count of coefs.
     */
    public int getCoefsCount() {
      return coefs_.size();
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @param index The index of the element to return.
     * @return The coefs at the given index.
     */
    public long getCoefs(int index) {
      return coefs_.getLong(index);
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @param index The index to set the value at.
     * @param value The coefs to set.
     * @return This builder for chaining.
     */
    public Builder setCoefs(
        int index, long value) {

      ensureCoefsIsMutable();
      coefs_.setLong(index, value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @param value The coefs to add.
     * @return This builder for chaining.
     */
    public Builder addCoefs(long value) {

      ensureCoefsIsMutable();
      coefs_.addLong(value);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @param values The coefs to add.
     * @return This builder for chaining.
     */
    public Builder addAllCoefs(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureCoefsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, coefs_);
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 coefs = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCoefs() {
      coefs_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString seed_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes seed = 2;</code>
     * @return The seed.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSeed() {
      return seed_;
    }
    /**
     * <code>bytes seed = 2;</code>
     * @param value The seed to set.
     * @return This builder for chaining.
     */
    public Builder setSeed(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      seed_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>bytes seed = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearSeed() {
      bitField0_ = (bitField0_ & ~0x00000002);
      seed_ = getDefaultInstance().getSeed();
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:msg.msg1)
  }

  // @@protoc_insertion_point(class_scope:msg.msg1)
  private static final proto.msg1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new proto.msg1();
  }

  public static proto.msg1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<msg1>
      PARSER = new com.google.protobuf.AbstractParser<msg1>() {
    @java.lang.Override
    public msg1 parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<msg1> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<msg1> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public proto.msg1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

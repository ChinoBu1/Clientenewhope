// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: msg.proto
// Protobuf Java Version: 4.26.0

package proto;

public final class msgproto {
  private msgproto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 26,
      /* patch= */ 0,
      /* suffix= */ "",
      msgproto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_msg_msg1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_msg_msg1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_msg_msg2_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_msg_msg2_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tmsg.proto\022\003msg\"#\n\004msg1\022\r\n\005coefs\030\001 \003(\003\022" +
      "\014\n\004seed\030\002 \001(\014\"$\n\004msg2\022\r\n\005coefs\030\001 \003(\003\022\r\n\005" +
      "hints\030\002 \003(\005B\023\n\005protoB\010msgprotoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_msg_msg1_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_msg_msg1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_msg_msg1_descriptor,
        new java.lang.String[] { "Coefs", "Seed", });
    internal_static_msg_msg2_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_msg_msg2_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_msg_msg2_descriptor,
        new java.lang.String[] { "Coefs", "Hints", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
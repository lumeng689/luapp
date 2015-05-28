// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request.proto

package org.luapp.practise.proto;

public final class RequestProto {
  private RequestProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.luapp.practise.proto.Request)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required int32 cmdType = 1;</code>
     */
    boolean hasCmdType();
    /**
     * <code>required int32 cmdType = 1;</code>
     */
    int getCmdType();
  }
  /**
   * Protobuf type {@code org.luapp.practise.proto.Request}
   */
  public static final class Request extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:org.luapp.practise.proto.Request)
      RequestOrBuilder {
    // Use Request.newBuilder() to construct.
    private Request(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private Request(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final Request defaultInstance;
    public static Request getDefaultInstance() {
      return defaultInstance;
    }

    public Request getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private Request(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              cmdType_ = input.readInt32();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.luapp.practise.proto.RequestProto.internal_static_org_luapp_practise_proto_Request_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.luapp.practise.proto.RequestProto.internal_static_org_luapp_practise_proto_Request_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.luapp.practise.proto.RequestProto.Request.class, org.luapp.practise.proto.RequestProto.Request.Builder.class);
    }

    public static com.google.protobuf.Parser<Request> PARSER =
        new com.google.protobuf.AbstractParser<Request>() {
      public Request parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Request(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<Request> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CMDTYPE_FIELD_NUMBER = 1;
    private int cmdType_;
    /**
     * <code>required int32 cmdType = 1;</code>
     */
    public boolean hasCmdType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 cmdType = 1;</code>
     */
    public int getCmdType() {
      return cmdType_;
    }

    private void initFields() {
      cmdType_ = 0;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasCmdType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, cmdType_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, cmdType_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static org.luapp.practise.proto.RequestProto.Request parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static org.luapp.practise.proto.RequestProto.Request parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.luapp.practise.proto.RequestProto.Request prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code org.luapp.practise.proto.Request}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.luapp.practise.proto.Request)
        org.luapp.practise.proto.RequestProto.RequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return org.luapp.practise.proto.RequestProto.internal_static_org_luapp_practise_proto_Request_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return org.luapp.practise.proto.RequestProto.internal_static_org_luapp_practise_proto_Request_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.luapp.practise.proto.RequestProto.Request.class, org.luapp.practise.proto.RequestProto.Request.Builder.class);
      }

      // Construct using org.luapp.practise.proto.RequestProto.Request.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        cmdType_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.luapp.practise.proto.RequestProto.internal_static_org_luapp_practise_proto_Request_descriptor;
      }

      public org.luapp.practise.proto.RequestProto.Request getDefaultInstanceForType() {
        return org.luapp.practise.proto.RequestProto.Request.getDefaultInstance();
      }

      public org.luapp.practise.proto.RequestProto.Request build() {
        org.luapp.practise.proto.RequestProto.Request result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public org.luapp.practise.proto.RequestProto.Request buildPartial() {
        org.luapp.practise.proto.RequestProto.Request result = new org.luapp.practise.proto.RequestProto.Request(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.cmdType_ = cmdType_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.luapp.practise.proto.RequestProto.Request) {
          return mergeFrom((org.luapp.practise.proto.RequestProto.Request)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.luapp.practise.proto.RequestProto.Request other) {
        if (other == org.luapp.practise.proto.RequestProto.Request.getDefaultInstance()) return this;
        if (other.hasCmdType()) {
          setCmdType(other.getCmdType());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasCmdType()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.luapp.practise.proto.RequestProto.Request parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.luapp.practise.proto.RequestProto.Request) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int cmdType_ ;
      /**
       * <code>required int32 cmdType = 1;</code>
       */
      public boolean hasCmdType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 cmdType = 1;</code>
       */
      public int getCmdType() {
        return cmdType_;
      }
      /**
       * <code>required int32 cmdType = 1;</code>
       */
      public Builder setCmdType(int value) {
        bitField0_ |= 0x00000001;
        cmdType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 cmdType = 1;</code>
       */
      public Builder clearCmdType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        cmdType_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:org.luapp.practise.proto.Request)
    }

    static {
      defaultInstance = new Request(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:org.luapp.practise.proto.Request)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_luapp_practise_proto_Request_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_org_luapp_practise_proto_Request_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rrequest.proto\022\030org.luapp.practise.prot" +
      "o\"\032\n\007Request\022\017\n\007cmdType\030\001 \002(\005B(\n\030org.lua" +
      "pp.practise.protoB\014RequestProto"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_org_luapp_practise_proto_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_luapp_practise_proto_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_org_luapp_practise_proto_Request_descriptor,
        new java.lang.String[] { "CmdType", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
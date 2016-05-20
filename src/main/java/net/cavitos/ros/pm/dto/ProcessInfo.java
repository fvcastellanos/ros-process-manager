package net.cavitos.ros.pm.dto;

public class ProcessInfo {
    private Long pId;
    private String name;
    private Long memoryUssage;

    public ProcessInfo() {

    }

    private ProcessInfo(Builder builder) {
        pId = builder.pId;
        name = builder.name;
        memoryUssage = builder.memoryUssage;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMemoryUssage() {
        return memoryUssage;
    }

    public void setMemoryUssage(Long memoryUssage) {
        this.memoryUssage = memoryUssage;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Long pId;
        private String name;
        private Long memoryUssage;

        private Builder() {
        }

        public Builder withPId(Long val) {
            pId = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withMemoryUssage(Long val) {
            memoryUssage = val;
            return this;
        }

        public ProcessInfo build() {
            return new ProcessInfo(this);
        }
    }
}

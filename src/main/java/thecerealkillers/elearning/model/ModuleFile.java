package thecerealkillers.elearning.model;

import java.util.Arrays;

/**
 * Created by cuvidk on 12/29/2015.
 */

public class ModuleFile {
    private String name;
    private String associatedCourse;
    private String associatedModule;
    private long size;
    private String type;
    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssociatedCourse() {
        return associatedCourse;
    }

    public void setAssociatedCourse(String associatedCourse) {
        this.associatedCourse = associatedCourse;
    }

    public String getAssociatedModule() {
        return associatedModule;
    }

    public void setAssociatedModule(String associatedModule) {
        this.associatedModule = associatedModule;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModuleFile)) return false;

        ModuleFile that = (ModuleFile) o;

        if (size != that.size) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (associatedCourse != null ? !associatedCourse.equals(that.associatedCourse) : that.associatedCourse != null)
            return false;
        if (associatedModule != null ? !associatedModule.equals(that.associatedModule) : that.associatedModule != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return Arrays.equals(content, that.content);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (associatedCourse != null ? associatedCourse.hashCode() : 0);
        result = 31 * result + (associatedModule != null ? associatedModule.hashCode() : 0);
        result = 31 * result + (int) (size ^ (size >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }

    @Override
    public String toString() {
        return "ModuleFile{" +
                "name='" + name + '\'' +
                ", associatedCourse='" + associatedCourse + '\'' +
                ", associatedModule='" + associatedModule + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}

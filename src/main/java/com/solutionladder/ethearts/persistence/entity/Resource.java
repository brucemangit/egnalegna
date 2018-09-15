package com.solutionladder.ethearts.persistence.entity;

import javax.persistence.Entity;

/**
 * Keeping track of files(Resources) in table Basically naming it as Resource
 * not to conflict with java File object.
 * 
 * @author Kaleb Woldearegay <kaleb@solutionladder.com>
 * @date 2018 - Aug - 11
 *
 */
@Entity
public class Resource extends DatedEntity {

    private String name;
    private String path;
    private float size;
    private String type;
    private String extension;
    private boolean blob;
    private boolean active;
    private boolean deleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isBlob() {
        return blob;
    }

    public void setBlob(boolean blob) {
        this.blob = blob;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

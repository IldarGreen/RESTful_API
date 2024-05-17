package com.vanesabo.backend.response;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

public final class ImagesResponse {
    private final UUID id;
    private final byte[] image;

    public ImagesResponse(UUID id, byte[] image) {
        this.id = id;
        this.image = image == null ? null : image.clone();
    }

    public UUID getId() {
        return id;
    }

    public byte[] getImage() {
        return image == null ? null : image.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ImagesResponse))
            return false;
        ImagesResponse that = (ImagesResponse) o;
        return Objects.equals(id, that.id) &&
                Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "ImagesResponse{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}

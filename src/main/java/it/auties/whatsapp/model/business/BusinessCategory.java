package it.auties.whatsapp.model.business;

import it.auties.whatsapp.model.request.Node;
import lombok.NonNull;

/**
 * A model class that represents a business category
 *
 * @param id   the non-null id
 * @param name the non-null display name
 */
public record BusinessCategory(@NonNull String id, @NonNull String name) {
    /**
     * Constructs a category from a node
     *
     * @param node a non-null node
     * @return a non-null category
     */
    public static BusinessCategory of(@NonNull Node node) {
        var id = node.attributes().getRequiredString("id");
        var name = node.contentAsString().orElseThrow();
        return new BusinessCategory(id, name);
    }
}

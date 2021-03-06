package org.bukkit.craftbukkit.tag;

import net.minecraft.tag.RegistryTagContainer;
import net.minecraft.util.Identifier;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.craftbukkit.util.CraftNamespacedKey;

import com.fungus_soft.bukkitfabric.interfaces.IMixinRegistryTagContainer;

public abstract class CraftTag<N, B extends Keyed> implements Tag<B> {

    private final RegistryTagContainer<N> registry;
    private final Identifier tag;
    private int version = -1;
    private net.minecraft.tag.Tag<N> handle;

    public CraftTag(RegistryTagContainer<N> registry, Identifier tag) {
        this.registry = registry;
        this.tag = tag;
    }

    protected net.minecraft.tag.Tag<N> getHandle() {
        if (version != ((IMixinRegistryTagContainer)(Object)registry).getVersion()) {
            handle = registry.getOrCreate(tag);
            version = ((IMixinRegistryTagContainer)(Object)registry).getVersion();
        }

        return handle;
    }

    @Override
    public NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(tag);
    }

}
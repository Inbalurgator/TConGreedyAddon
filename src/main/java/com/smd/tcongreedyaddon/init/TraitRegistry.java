package com.smd.tcongreedyaddon.init;

import com.smd.tcongreedyaddon.traits.modifiers.ModTest;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TraitRegistry {

    public static ModTest test;


    public static final List<ModifierTrait> REGISTERED_TRAITS = new ArrayList<>();

    public static void init() {

        REGISTERED_TRAITS.add(test = new ModTest());
        test.addItem("stone", 1, 1);

    }

    public static Stream<ModifierTrait> stream() {
        return REGISTERED_TRAITS.stream();
    }
}


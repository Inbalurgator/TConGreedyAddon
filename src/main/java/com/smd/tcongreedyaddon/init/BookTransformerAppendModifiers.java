package com.smd.tcongreedyaddon.init;

import com.smd.tcongreedyaddon.proxy.ClientProxy;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;

public class BookTransformerAppendModifiers extends SectionTransformer {

    public BookTransformerAppendModifiers() {
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        TraitRegistry.stream().forEach(trait -> {
            PageData page = new PageData();
            page.source = ClientProxy.TCON_BOOK_REPO;
            page.parent = section;
            page.type = "modifier";
            page.data = "modifiers/" + trait.identifier + ".json";
            section.pages.add(page);
            page.load();
            listing.addEntry(trait.getLocalizedName(), page);
        });
    }

}


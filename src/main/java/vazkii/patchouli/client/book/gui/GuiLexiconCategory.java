package vazkii.patchouli.client.book.gui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import vazkii.patchouli.client.book.BookCategory;
import vazkii.patchouli.client.book.BookEntry;
import vazkii.patchouli.client.book.BookRegistry;
import vazkii.patchouli.client.book.gui.button.GuiButtonCategory;

public class GuiLexiconCategory extends GuiLexiconEntryList {

	BookCategory category;
	
	public GuiLexiconCategory(BookCategory category) {
		this.category = category;
	}

	@Override
	protected String getName() {
		return category.getName();
	}

	@Override
	protected String getDescriptionText() {
		return category.getDescription();
	}

	@Override
	protected Collection<BookEntry> getEntries() {
		return category.getEntries();
	}
	
	@Override
	protected void addSubcategoryButtons() {
		int i = 0;
		List<BookCategory> categories = new ArrayList(BookRegistry.INSTANCE.categories.values());
		Collections.sort(categories);
		
		for(BookCategory ocategory : categories) {
			if(ocategory.getParentCategory() != category)
				continue;
			
			int x = LEFT_PAGE_X + 10 + (i % 4) * 24;
			int y = TOP_PADDING + PAGE_HEIGHT - 68;
			
			GuiButton button = new GuiButtonCategory(this, x, y, ocategory);
			buttonList.add(button);
			dependentButtons.add(button);
			
			i++;
		}
	}
	
	@Override
	protected boolean doesEntryCountForProgress(BookEntry entry) {
		return entry.getCategory() == category;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj == this || (obj instanceof GuiLexiconCategory && ((GuiLexiconCategory) obj).category == category && ((GuiLexiconCategory) obj).page == page);
	}
	
	@Override
	boolean canBeOpened() {
		return !category.isLocked() && !equals(Minecraft.getMinecraft().currentScreen);
	}
	
}

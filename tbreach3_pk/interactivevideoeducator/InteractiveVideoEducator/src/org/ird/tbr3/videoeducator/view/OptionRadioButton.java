package org.ird.tbr3.videoeducator.view;

import org.ird.tbr3.videoeducator.common.App;
import org.ird.tbr3.videoeducator.common.FontManager;
import org.ird.tbr3.videoeducator.database.data_access.DataProvider;
import org.ird.tbr3.videoeducator.database.model.Language;
import org.ird.tbr3.videoeducator.database.model.LocalizedOption;
import org.ird.tbr3.videoeducator.database.model.Option;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RadioButton;

public class OptionRadioButton extends RadioButton {

	int optionType;
	LocalizedOption localizedOption;
	private String uuid;
	
	public String getConceptName() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public OptionRadioButton(Context context, int optionType, LocalizedOption localizedOption) {
		super(context);
		this.optionType = optionType;
		this.localizedOption = localizedOption;
		
		setButtonDrawable(android.R.color.transparent);
		setGravity(Gravity.CENTER_HORIZONTAL);
		setTextSize(28f);
		setPadding(0, 10, 0, 10);
		if(App.LANGUAGE.getLanguageId().equals(Language.URDU_PAK)) {
			
			setTypeface(FontManager.global.getTypeFace(Language.URDU_PAK));
			setTextSize(40f);
		}
		
		uuid = DataProvider.getInstance().getOption(localizedOption.getOptionId()).getConceptName();
	}
	
	public OptionRadioButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public OptionRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public OptionRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public int getOptionType() {
		return optionType;
	}
	
	public Option getOption() {
		return DataProvider.getInstance().getOption(getOptionId());
	}
	public int getOptionId() {
		return localizedOption.getOptionId();
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	public LocalizedOption getLocalizedOption() {
		return localizedOption;
	}

	public void setLocalizedOption(LocalizedOption option) {
		this.localizedOption = option;
	}
	
}

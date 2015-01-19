package org.ird.tbr3.videoeducator.database.data_access;

import java.util.ArrayList;
import java.util.List;

import org.ird.tbr3.videoeducator.database.model.Language;
import org.ird.tbr3.videoeducator.database.model.LocalizedOption;
import org.ird.tbr3.videoeducator.database.model.LocalizedQuestion;
import org.ird.tbr3.videoeducator.database.model.LocalizedVideo;
import org.ird.tbr3.videoeducator.database.model.Option;
import org.ird.tbr3.videoeducator.database.model.Question;
import org.ird.tbr3.videoeducator.database.model.QuestionType;
import org.ird.tbr3.videoeducator.database.model.Video;

public class DataProvider {
	
	private static DataProvider dataProvider;
	List<Language> languagesList;
	List<QuestionType> questionTypesList;
	List<Video> videosList;
	List<LocalizedVideo> localizedVideosList;
	List<Question> questionsList;
	List<LocalizedQuestion> localizedQuestionsList;
	List<Option> optionsList;
	List<LocalizedOption> localizedOptionsList;

	public static DataProvider getInstance() {
		if(dataProvider == null) {
			return new DataProvider();
		}
		
		return dataProvider;
	}
	
	private DataProvider() {
		languagesList = new ArrayList<Language>();
		languagesList.add(new Language(Language.ENGLISH_US, "English-US", "/DCIM/TBR3/Sounds/Resources/english.wav", Language.LTR_SCRIPT, "/DCIM/TBR3/Sounds/English -- 01.wav", "/DCIM/TBR3/Sounds/English -- 08.wav", 13000, 17000, "English"));
		languagesList.add(new Language(Language.URDU_PAK, "Urdu-Pakistan", "/DCIM/TBR3/Sounds/Resources/urdu.wav", Language.RTL_SCRIPT, "/DCIM/TBR3/Sounds/Urdu -- 01.wav", "/DCIM/TBR3/Sounds/Urdu -- 08.wav", 20000, 24000, "Urdu", "Punjabi", "Pushto", "Balochi", "Sindhi", "Saraiki", "Bangla"));
		languagesList.add(new Language(Language.BENGLA_BAN, "Bengali-Bangladesh", "/DCIM/TBR3/Sounds/Resources/bengali.wav", Language.LTR_SCRIPT,  "/DCIM/TBR3/Sounds/English -- 01.wav", "/DCIM/TBR3/Sounds/English -- 08.wav", 0, 0, "Bangla", "Sylheti", "Hindi", "Urdu" ));

		questionTypesList = new ArrayList<QuestionType>();
		questionTypesList.add(new QuestionType(0, "Single Select Image"));
		questionTypesList.add(new QuestionType(1, "Multi Select Image"));
		questionTypesList.add(new QuestionType(2, "Single Select Text"));
		questionTypesList.add(new QuestionType(2, "Multi Select Text"));
		
		videosList = new ArrayList<Video>();
		videosList.add(new Video(0, "Sputum Instructions","Sputum Instructions"));
		
		localizedVideosList = new ArrayList<LocalizedVideo>();
		localizedVideosList.add(new LocalizedVideo(0, 0, "en-US", "How to give sputum", "description", "/DCIM/TBR3/Videos/Sputum Instructions - English.mp4"));
		localizedVideosList.add(new LocalizedVideo(1, 0, "ur-PK", "بلغم دینے کاطریقہ", "tafseeel", "/DCIM/TBR3/Videos/Sputum Instructions - Urdu.mp4"));
		localizedVideosList.add(new LocalizedVideo(1, 0, "bn-BN", "শ্লেষ্মা এর প্রক্রিয়া", "tafseeel", "/DCIM/TBR3/Videos/clip1.mp4"));
		
		questionsList = new ArrayList<Question>();
		questionsList.add(new Question(0, 0, QuestionType.SINGLE_SELECT_TEXT, Question.BEFORE, "Have you ever tried to cough up a sputum sample before?", "Tried To Cough Up"));
		questionsList.add(new Question(1, 0, QuestionType.SINGLE_SELECT_TEXT, Question.BEFORE, "Have you ever been instructed on how to cough up sputum?", "Coughing Instructions"));
		questionsList.add(new Question(2, 0, QuestionType.SINGLE_SELECT_TEXT, Question.BEFORE, "Do you think you can tell the difference between sputum and saliva samples?", "Sample Differenciation Before"));
		questionsList.add(new Question(3, 0, QuestionType.SINGLE_SELECT_IMAGE, Question.BEFORE, "Tap the picture that shows a good sample of sputum to submit for TB testing.", "Tapped Sample Before"));

		questionsList.add(new Question(4, 0, QuestionType.SINGLE_SELECT_TEXT, Question.AFTER, "Now that you have seen the video, do you think you can tell the difference between sputum and saliva samples?", "Sample Differenciation After"));
		questionsList.add(new Question(5, 0, QuestionType.SINGLE_SELECT_IMAGE, Question.AFTER, "Tap the picture that you think shows a good sample of sputum to submit for TB testing.", "Tapped Sample After"));
		
		questionsList.add(new Question(6, 0, QuestionType.OTHER, Question.NONE, "User starting time", "User Starting Time"));
		questionsList.add(new Question(7, 0, QuestionType.OTHER, Question.NONE, "Patient Starting Time", "Patient Starting Time"));
		questionsList.add(new Question(8, 0, QuestionType.OTHER, Question.NONE, "Video App Ending Time", "Video App Ending Time"));
		questionsList.add(new Question(9, 0, QuestionType.OTHER, Question.NONE, "Language", "Language"));
		questionsList.add(new Question(10, 0, QuestionType.OTHER, Question.NONE, "Mother tounge", "Mother Tounge"));
		
		questionsList.add(new Question(11, 0, QuestionType.OTHER, Question.NONE, "Sufficient sputum", "Sufficient Sputum"));
		questionsList.add(new Question(12, 0, QuestionType.OTHER, Question.NONE, "Insufficient sputum", "Insufficient Sputum"));
		questionsList.add(new Question(13, 0, QuestionType.OTHER, Question.NONE, "Urine", "Urine"));
		questionsList.add(new Question(14, 0, QuestionType.OTHER, Question.NONE, "Saliva Image", "Saliva Image"));
		
		localizedQuestionsList = new ArrayList<LocalizedQuestion>();
		localizedQuestionsList.add(new LocalizedQuestion(0, 0, "en-US", "Have you ever tried to cough up a sputum sample before?", "/DCIM/TBR3/Sounds/English -- 02.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(1, 0, "ur-PK", "کیا آپنے پہلے کبھی اپنے بلغم کا نمونہ کھانسنے کی کوشش کی ہے؟", "/DCIM/TBR3/Sounds/Urdu -- 02.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(2, 1, "en-US", "Have you ever been instructed on how to cough up sputum?", "/DCIM/TBR3/Sounds/English -- 03.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(3, 1, "ur-PK", "کیا آپکو پہلے کبھی بلغم کھانسنے اور خارج کرنے سے متعلق ہدایات فراہم کی گئ ہیں؟", "/DCIM/TBR3/Sounds/Urdu -- 03.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(4, 2, "en-US", "Do you think you can tell the difference between sputum and saliva samples?", "/DCIM/TBR3/Sounds/English -- 04.wav"));		
		localizedQuestionsList.add(new LocalizedQuestion(5, 2, "ur-PK", "کیا آپ بلغم اور تھوک کےنمونےمیں فرق واضح طور پر جانتے ہیں؟", "/DCIM/TBR3/Sounds/Urdu -- 04.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(6, 3, "en-US", "Tap the picture that shows a good sample of sputum to submit for TB testing.", "/DCIM/TBR3/Sounds/English -- 05.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(7, 3, "ur-PK", "برائےمہربانی اس تصویر کو دبائیں جو آپکےنزدیک ٹی بی کے معائنے کے لیئے اچھابلغم کا نمونہ ہے۔", "/DCIM/TBR3/Sounds/Urdu -- 05.wav"));
		
		localizedQuestionsList.add(new LocalizedQuestion(8, 4, "en-US", "Now that you have seen the video, do you think you can tell the difference between sputum and saliva samples?", "/DCIM/TBR3/Sounds/English -- 06.wav"));		
		localizedQuestionsList.add(new LocalizedQuestion(9, 4, "ur-PK", "وڈیو دیکھنے کے بعد کیا آپ بلغم اور تھوک کے نمونے میں واضح طور پر فرق کرسکتے ہیں؟", "/DCIM/TBR3/Sounds/Urdu -- 06.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(10, 5, "en-US", "Tap the picture that you think shows a good sample of sputum to submit for TB testing.", "/DCIM/TBR3/Sounds/English -- 07-0.wav"));
		localizedQuestionsList.add(new LocalizedQuestion(11, 5, "ur-PK", "برائےمہربانی اس تصویر کو دبائیں جو آپکےنزدیک ٹی بی کے معائنے کے لیئے اچھابلغم کا نمونہ ہے۔", "/DCIM/TBR3/Sounds/Urdu -- 07-0.wav"));
		
		
		optionsList = new ArrayList<Option>();
		optionsList.add(new Option(0, 0, "True", "Yes"));
		optionsList.add(new Option(1, 0, "False", "No"));
		optionsList.add(new Option(2, 0, "Unknown", "Do not know"));
		optionsList.add(new Option(3, 1, "True", "Yes"));
		optionsList.add(new Option(4, 1, "False", "No"));
		optionsList.add(new Option(5, 1, "Unknown", "Do not know"));
		optionsList.add(new Option(6, 2, "True", "Yes"));
		optionsList.add(new Option(7, 2, "False", "No"));
		optionsList.add(new Option(8, 2, "Unknown", "Do not know"));
		optionsList.add(new Option(9, 3, "Sufficient Sputum", "Sufficient sputum", true));
		optionsList.add(new Option(10, 3, "Insufficient Sputum", "Insufficient sputum", false));
		optionsList.add(new Option(11, 3, "Urine", "Urine", false));
		optionsList.add(new Option(12, 3, "Saliva Image", "Saliva Image", false));

		optionsList.add(new Option(13, 4, "True", "Yes"));
		optionsList.add(new Option(14, 4, "False", "No"));
		optionsList.add(new Option(15, 4, "Unknown", "Do not know"));

		optionsList.add(new Option(16, 5, "Sufficient Sputum", "Sufficient sputum", true));
		optionsList.add(new Option(17, 5, "Insufficient Sputum", "Insufficient sputum", false));
		optionsList.add(new Option(18, 5, "Urine", "Urine", false));
		optionsList.add(new Option(19, 5, "Saliva Image", "Saliva Image", false));
		
		localizedOptionsList = new ArrayList<LocalizedOption>();
		localizedOptionsList.add(new LocalizedOption(0, 0, Language.ENGLISH_US, "Yes", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(1, 0, Language.URDU_PAK, "ہاں", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(2, 1, Language.ENGLISH_US, "No", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(3, 1, Language.URDU_PAK, "نہیں", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(4, 2, Language.ENGLISH_US, "Do not Know", "/DCIM/TBR3/Symbols/triangle.png", ""));
		localizedOptionsList.add(new LocalizedOption(5, 2, Language.URDU_PAK, "معلوم نہیں", "/DCIM/TBR3/Symbols/triangle_rtl.png", ""));
		localizedOptionsList.add(new LocalizedOption(6, 3, Language.ENGLISH_US, "Yes", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(7, 3, Language.URDU_PAK, "ہاں", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(8, 4, Language.ENGLISH_US, "No", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(9, 4, Language.URDU_PAK, "نہیں", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(10, 5, Language.ENGLISH_US, "Do not Know", "/DCIM/TBR3/Symbols/triangle.png", ""));
		localizedOptionsList.add(new LocalizedOption(11, 5, Language.URDU_PAK, "معلوم نہیں", "/DCIM/TBR3/Symbols/triangle_rtl.png", ""));
		localizedOptionsList.add(new LocalizedOption(12, 6, Language.ENGLISH_US, "Yes", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(13, 6, Language.URDU_PAK, "ہاں", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(14, 7, Language.ENGLISH_US, "No", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(15, 7, Language.URDU_PAK, "نہیں", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(16, 8, Language.ENGLISH_US, "Do not Know", "/DCIM/TBR3/Symbols/triangle.png", ""));
		localizedOptionsList.add(new LocalizedOption(17, 8, Language.URDU_PAK, "معلوم نہیں", "/DCIM/TBR3/Symbols/triangle_rtl.png", ""));
		
		localizedOptionsList.add(new LocalizedOption(18, 9, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/sputum_covering_bottom_of_cup.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(19, 9, Language.URDU_PAK, null, "/DCIM/TBR3/Images/sputum_covering_bottom_of_cup.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(20, 10, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/speck_of_sputum_in_otherwise_empty_cup.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(21, 10, Language.URDU_PAK, null, "/DCIM/TBR3/Images/speck_of_sputum_in_otherwise_empty_cup.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(22, 11, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/urine.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(23, 11, Language.URDU_PAK, null, "/DCIM/TBR3/Images/urine.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(24, 12, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/sliva.jpg", ""));
		localizedOptionsList.add(new LocalizedOption(25, 12, Language.URDU_PAK, null, "/DCIM/TBR3/Images/sliva.jpg", ""));
		
		localizedOptionsList.add(new LocalizedOption(26, 13, Language.ENGLISH_US, "Yes", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(27, 13, Language.URDU_PAK, "ہاں", "/DCIM/TBR3/Symbols/circle.png", ""));
		localizedOptionsList.add(new LocalizedOption(28, 14, Language.ENGLISH_US, "No", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(29, 14, Language.URDU_PAK, "نہیں", "/DCIM/TBR3/Symbols/square.png", ""));
		localizedOptionsList.add(new LocalizedOption(30, 15, Language.ENGLISH_US, "Do not Know", "/DCIM/TBR3/Symbols/triangle.png", ""));
		localizedOptionsList.add(new LocalizedOption(31, 15, Language.URDU_PAK, "معلوم نہیں", "/DCIM/TBR3/Symbols/triangle_rtl.png", ""));
		
		localizedOptionsList.add(new LocalizedOption(32, 16, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/sputum_covering_bottom_of_cup.jpg", "/DCIM/TBR3/Sounds/English -- 07-2.wav"));
		localizedOptionsList.add(new LocalizedOption(33, 16, Language.URDU_PAK, null, "/DCIM/TBR3/Images/sputum_covering_bottom_of_cup.jpg", "/DCIM/TBR3/Sounds/Urdu -- 07-2.wav"));
		localizedOptionsList.add(new LocalizedOption(34, 17, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/speck_of_sputum_in_otherwise_empty_cup.jpg", "/DCIM/TBR3/Sounds/English -- 07-1.wav"));
		localizedOptionsList.add(new LocalizedOption(35, 17, Language.URDU_PAK, null, "/DCIM/TBR3/Images/speck_of_sputum_in_otherwise_empty_cup.jpg", "/DCIM/TBR3/Sounds/Urdu -- 07-1.wav"));
		localizedOptionsList.add(new LocalizedOption(36, 18, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/urine.jpg", "/DCIM/TBR3/Sounds/English -- 07-3.wav"));
		localizedOptionsList.add(new LocalizedOption(37, 18, Language.URDU_PAK, null, "/DCIM/TBR3/Images/urine.jpg", "/DCIM/TBR3/Sounds/Urdu -- 07-3.wav"));
		localizedOptionsList.add(new LocalizedOption(38, 19, Language.ENGLISH_US, null, "/DCIM/TBR3/Images/sliva.jpg", "/DCIM/TBR3/Sounds/English -- 07-4.wav"));
		localizedOptionsList.add(new LocalizedOption(39, 19, Language.URDU_PAK, null, "/DCIM/TBR3/Images/sliva.jpg", "/DCIM/TBR3/Sounds/Urdu -- 07-4.wav"));

	}
	
	
	/////////////////////////////////////Language//////////////////////////////////////
	
	public List<Language> getLanguages() {
		
		return languagesList;
	}
	
	public Language getLanguage(String languageId) {
		if ("en-US".equals(languageId)) {
			return languagesList.get(0);
		} else if ("ur-PK".equals(languageId)) {
			return languagesList.get(1);
		} else if ("bn-BAN".equals(languageId)) {
			return languagesList.get(2);
		}

		return null;
	}
	
	public String getLanguageAudioPath(String languageId) {
		for(Language l:languagesList) {
			if(l.getLanguageId().equals(languageId)) {
				return l.getAudioPath();
			}
		}
		
		return null;
	}
	
	/////////////////////////////////////Question Type//////////////////////////////////////
	
	public List<QuestionType> getQuestionTypes() {	
		
		return questionTypesList;
	}
	
	
	/////////////////////////////////////Videos//////////////////////////////////////
	
	public List<Video> getVideos() {
		
		return videosList;
	}
	
	public Video getVideo(int videoId) {
		for(Video v:videosList) {
			if(videoId == v.getVideoId()) {
				return v;
			}
		}
		
		return null;
	}
	
	public LocalizedVideo getLocalizedVideos(int videoId, String languageId) {
		for(LocalizedVideo lv:localizedVideosList) {
			if(videoId == lv.getVideoId() && languageId.equals(lv.getLanguageId())){
				return lv;
			}
		}
		
		return null;
	}
	
	
	/////////////////////////////////////Questions/////////////////////////////////////
	
	public List<Question> getQuestions(int videoId) {
		List<Question> toReturn = new ArrayList<Question>();
		for(Question q:questionsList) {
			if(q.getVideoId() == videoId) {
				toReturn.add(q);
			}
		}
		
		return toReturn;
	}
	
	public Question getQuestion(int questionId) {
		for(Question q:questionsList) {
			 if(questionId == q.getQuestionId()) {
				 return q;
			 }
		}
		
		return null;
	}
	
	public Question getQuestion(String uuid) {
		for(Question q:questionsList) {
			 if(uuid.equals(q.getConseptName())) {
				 return q;
			 }
		}
		
		return null;
	}
	
	public List<LocalizedQuestion> getLocalizedQuestions(int videoId, String languageId) {
		
		List<LocalizedQuestion> toReturn = new ArrayList<LocalizedQuestion>();
		for(LocalizedQuestion lq:localizedQuestionsList) {
			
			if(videoId==getQuestion(lq.getQuestionId()).getVideoId()
					&& languageId.equals(lq.getLanguageId())) {
				toReturn.add(lq);
			}
		}
		
		return toReturn;
	}
	
	public LocalizedQuestion getLocalizedQuestion(int questionId, String languageId) {
		for(LocalizedQuestion lq:localizedQuestionsList) {
			if(lq.getQuestionId()==questionId && lq.getLanguageId().equals(languageId)) {
				return lq;
			}
		}
		return null;
	}
	
	
	/////////////////////////////////////Options//////////////////////////////////////
	
	public List<Option> getOptions(int questionId) {
		List<Option> toReturn = new ArrayList<Option>();
		for(Option o:optionsList) {
			if(o.getQuestionId()==questionId) {
				toReturn.add(o);
			}
		}
		return toReturn;
	}
	
	public Option getOption(int optionId) {
		for(Option o:optionsList) {
			if(o.getOptionId() == optionId) {
				return o;
			}
		}
		
		return null;
	}
	
	public Option getOption(String uuid) {
		for(Option o:optionsList) {
			if(uuid.equals(o.getConceptName	())) {
				return o;
			}
		}
		
		return null;
	}
	
	public List<LocalizedOption> getLocalizedOptions(int questionId, String languageId) {
		List<LocalizedOption> toReturn = new ArrayList<LocalizedOption>();
		for(LocalizedOption lo:localizedOptionsList) {
			if(questionId == getOption(lo.getOptionId()).getQuestionId()
					&& languageId.equals(lo.getLanguageId())) {
				toReturn.add(lo);
			}
		}
		
		return toReturn;
	}
 }

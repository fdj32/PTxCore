package serial;

/**
 * 0140-05252-0904 CPX+ EMV Emulation.pdf {Page.121/184} {6A. Interac Debit
 * Sequence Initialization}
 * 
 * @author nfeng
 *
 */
public class Cpx6AInteracDebitSequenceInitRequest extends CpxRequest {

	private String swipeCardPromptLineNumber;
	private String swipeCardPromptEnglish;
	private String swipeCardPromptFrench;

	private String amountOKpromptLineNumber;
	private String amountOKPromptEnglish;
	private String amountOKPromptFrench;

	private String enterTipPromptLineNumber;
	private String enterTipEnglishPromptIndexOfMAC;
	private String enterTipEnglishPrompt;
	private String enterTipFrenchPromptIndexOfMAC;
	private String enterTipFrenchPrompt;

	private String cashBackPromptLineNumber;
	private String cashBackEnglishPromptIndexOfMAC;
	private String cashBackEnglishPrompt;
	private String cashBackFrenchPromptIndexOfMAC;
	private String cashBackFrenchPrompt;

	private String totalOKpromptLineNumber;
	private String totalOKPromptEnglish;
	private String totalOKPromptFrench;

	private String selectAccountpromptLineNumber;
	private String selectAccountPromptEnglish;
	private String selectAccountPromptFrench;

	private String pinEntryPromptLineNumber;
	private String pinEntryEnglishPromptIndexOfMAC;
	private String pinEntryEnglishPrompt;
	private String pinEntryFrenchPromptIndexOfMAC;
	private String pinEntryFrenchPrompt;

	private String pinErrorMessageLineNumber;
	private String pinErrorMessageEnglishPrompt;
	private String pinErrorMessageFrenchPrompt;

	private String serviceCodeList; // "120220         "

	public String getSwipeCardPromptLineNumber() {
		return swipeCardPromptLineNumber;
	}

	public void setSwipeCardPromptLineNumber(String swipeCardPromptLineNumber) {
		this.swipeCardPromptLineNumber = swipeCardPromptLineNumber;
	}

	public String getSwipeCardPromptEnglish() {
		return swipeCardPromptEnglish;
	}

	public void setSwipeCardPromptEnglish(String swipeCardPromptEnglish) {
		this.swipeCardPromptEnglish = swipeCardPromptEnglish;
	}

	public String getSwipeCardPromptFrench() {
		return swipeCardPromptFrench;
	}

	public void setSwipeCardPromptFrench(String swipeCardPromptFrench) {
		this.swipeCardPromptFrench = swipeCardPromptFrench;
	}

	public String getAmountOKpromptLineNumber() {
		return amountOKpromptLineNumber;
	}

	public void setAmountOKpromptLineNumber(String amountOKpromptLineNumber) {
		this.amountOKpromptLineNumber = amountOKpromptLineNumber;
	}

	public String getAmountOKPromptEnglish() {
		return amountOKPromptEnglish;
	}

	public void setAmountOKPromptEnglish(String amountOKPromptEnglish) {
		this.amountOKPromptEnglish = amountOKPromptEnglish;
	}

	public String getAmountOKPromptFrench() {
		return amountOKPromptFrench;
	}

	public void setAmountOKPromptFrench(String amountOKPromptFrench) {
		this.amountOKPromptFrench = amountOKPromptFrench;
	}

	public String getEnterTipPromptLineNumber() {
		return enterTipPromptLineNumber;
	}

	public void setEnterTipPromptLineNumber(String enterTipPromptLineNumber) {
		this.enterTipPromptLineNumber = enterTipPromptLineNumber;
	}

	public String getEnterTipEnglishPromptIndexOfMAC() {
		return enterTipEnglishPromptIndexOfMAC;
	}

	public void setEnterTipEnglishPromptIndexOfMAC(
			String enterTipEnglishPromptIndexOfMAC) {
		this.enterTipEnglishPromptIndexOfMAC = enterTipEnglishPromptIndexOfMAC;
	}

	public String getEnterTipEnglishPrompt() {
		return enterTipEnglishPrompt;
	}

	public void setEnterTipEnglishPrompt(String enterTipEnglishPrompt) {
		this.enterTipEnglishPrompt = enterTipEnglishPrompt;
	}

	public String getEnterTipFrenchPromptIndexOfMAC() {
		return enterTipFrenchPromptIndexOfMAC;
	}

	public void setEnterTipFrenchPromptIndexOfMAC(
			String enterTipFrenchPromptIndexOfMAC) {
		this.enterTipFrenchPromptIndexOfMAC = enterTipFrenchPromptIndexOfMAC;
	}

	public String getEnterTipFrenchPrompt() {
		return enterTipFrenchPrompt;
	}

	public void setEnterTipFrenchPrompt(String enterTipFrenchPrompt) {
		this.enterTipFrenchPrompt = enterTipFrenchPrompt;
	}

	public String getCashBackPromptLineNumber() {
		return cashBackPromptLineNumber;
	}

	public void setCashBackPromptLineNumber(String cashBackPromptLineNumber) {
		this.cashBackPromptLineNumber = cashBackPromptLineNumber;
	}

	public String getCashBackEnglishPromptIndexOfMAC() {
		return cashBackEnglishPromptIndexOfMAC;
	}

	public void setCashBackEnglishPromptIndexOfMAC(
			String cashBackEnglishPromptIndexOfMAC) {
		this.cashBackEnglishPromptIndexOfMAC = cashBackEnglishPromptIndexOfMAC;
	}

	public String getCashBackEnglishPrompt() {
		return cashBackEnglishPrompt;
	}

	public void setCashBackEnglishPrompt(String cashBackEnglishPrompt) {
		this.cashBackEnglishPrompt = cashBackEnglishPrompt;
	}

	public String getCashBackFrenchPromptIndexOfMAC() {
		return cashBackFrenchPromptIndexOfMAC;
	}

	public void setCashBackFrenchPromptIndexOfMAC(
			String cashBackFrenchPromptIndexOfMAC) {
		this.cashBackFrenchPromptIndexOfMAC = cashBackFrenchPromptIndexOfMAC;
	}

	public String getCashBackFrenchPrompt() {
		return cashBackFrenchPrompt;
	}

	public void setCashBackFrenchPrompt(String cashBackFrenchPrompt) {
		this.cashBackFrenchPrompt = cashBackFrenchPrompt;
	}

	public String getTotalOKpromptLineNumber() {
		return totalOKpromptLineNumber;
	}

	public void setTotalOKpromptLineNumber(String totalOKpromptLineNumber) {
		this.totalOKpromptLineNumber = totalOKpromptLineNumber;
	}

	public String getTotalOKPromptEnglish() {
		return totalOKPromptEnglish;
	}

	public void setTotalOKPromptEnglish(String totalOKPromptEnglish) {
		this.totalOKPromptEnglish = totalOKPromptEnglish;
	}

	public String getTotalOKPromptFrench() {
		return totalOKPromptFrench;
	}

	public void setTotalOKPromptFrench(String totalOKPromptFrench) {
		this.totalOKPromptFrench = totalOKPromptFrench;
	}

	public String getSelectAccountpromptLineNumber() {
		return selectAccountpromptLineNumber;
	}

	public void setSelectAccountpromptLineNumber(
			String selectAccountpromptLineNumber) {
		this.selectAccountpromptLineNumber = selectAccountpromptLineNumber;
	}

	public String getSelectAccountPromptEnglish() {
		return selectAccountPromptEnglish;
	}

	public void setSelectAccountPromptEnglish(String selectAccountPromptEnglish) {
		this.selectAccountPromptEnglish = selectAccountPromptEnglish;
	}

	public String getSelectAccountPromptFrench() {
		return selectAccountPromptFrench;
	}

	public void setSelectAccountPromptFrench(String selectAccountPromptFrench) {
		this.selectAccountPromptFrench = selectAccountPromptFrench;
	}

	public String getPinEntryPromptLineNumber() {
		return pinEntryPromptLineNumber;
	}

	public void setPinEntryPromptLineNumber(String pinEntryPromptLineNumber) {
		this.pinEntryPromptLineNumber = pinEntryPromptLineNumber;
	}

	public String getPinEntryEnglishPromptIndexOfMAC() {
		return pinEntryEnglishPromptIndexOfMAC;
	}

	public void setPinEntryEnglishPromptIndexOfMAC(
			String pinEntryEnglishPromptIndexOfMAC) {
		this.pinEntryEnglishPromptIndexOfMAC = pinEntryEnglishPromptIndexOfMAC;
	}

	public String getPinEntryEnglishPrompt() {
		return pinEntryEnglishPrompt;
	}

	public void setPinEntryEnglishPrompt(String pinEntryEnglishPrompt) {
		this.pinEntryEnglishPrompt = pinEntryEnglishPrompt;
	}

	public String getPinEntryFrenchPromptIndexOfMAC() {
		return pinEntryFrenchPromptIndexOfMAC;
	}

	public void setPinEntryFrenchPromptIndexOfMAC(
			String pinEntryFrenchPromptIndexOfMAC) {
		this.pinEntryFrenchPromptIndexOfMAC = pinEntryFrenchPromptIndexOfMAC;
	}

	public String getPinEntryFrenchPrompt() {
		return pinEntryFrenchPrompt;
	}

	public void setPinEntryFrenchPrompt(String pinEntryFrenchPrompt) {
		this.pinEntryFrenchPrompt = pinEntryFrenchPrompt;
	}

	public String getPinErrorMessageLineNumber() {
		return pinErrorMessageLineNumber;
	}

	public void setPinErrorMessageLineNumber(String pinErrorMessageLineNumber) {
		this.pinErrorMessageLineNumber = pinErrorMessageLineNumber;
	}

	public String getPinErrorMessageEnglishPrompt() {
		return pinErrorMessageEnglishPrompt;
	}

	public void setPinErrorMessageEnglishPrompt(
			String pinErrorMessageEnglishPrompt) {
		this.pinErrorMessageEnglishPrompt = pinErrorMessageEnglishPrompt;
	}

	public String getPinErrorMessageFrenchPrompt() {
		return pinErrorMessageFrenchPrompt;
	}

	public void setPinErrorMessageFrenchPrompt(
			String pinErrorMessageFrenchPrompt) {
		this.pinErrorMessageFrenchPrompt = pinErrorMessageFrenchPrompt;
	}

	public String getServiceCodeList() {
		return serviceCodeList;
	}

	public void setServiceCodeList(String serviceCodeList) {
		this.serviceCodeList = serviceCodeList;
	}

	public Cpx6AInteracDebitSequenceInitRequest() {
		super();
		this.setMessageType("6A.");
		this.setSwipeCardPromptLineNumber("2");
		this.setAmountOKpromptLineNumber("2");
		this.setEnterTipPromptLineNumber("2");
		this.setCashBackPromptLineNumber("2");
		this.setTotalOKpromptLineNumber("2");
		this.setSelectAccountpromptLineNumber("2");
		this.setPinEntryPromptLineNumber("2");
		this.setPinErrorMessageLineNumber("2");
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getMessageType());
		sb.append(this.getSwipeCardPromptLineNumber());
		sb.append(this.getSwipeCardPromptEnglish()).append(UTFUtils.FS);
		sb.append(this.getSwipeCardPromptFrench()).append(UTFUtils.FS);

		sb.append(this.getAmountOKpromptLineNumber());
		sb.append(this.getAmountOKPromptEnglish()).append(UTFUtils.FS);
		sb.append(this.getAmountOKPromptFrench()).append(UTFUtils.FS);

		sb.append(this.getEnterTipPromptLineNumber());
		sb.append(this.getEnterTipEnglishPromptIndexOfMAC());
		sb.append(this.getEnterTipEnglishPrompt()).append(UTFUtils.FS);
		sb.append(this.getEnterTipFrenchPromptIndexOfMAC());
		sb.append(this.getEnterTipFrenchPrompt()).append(UTFUtils.FS);

		sb.append(this.getCashBackPromptLineNumber());
		sb.append(this.getCashBackEnglishPromptIndexOfMAC());
		sb.append(this.getCashBackEnglishPrompt()).append(UTFUtils.FS);
		sb.append(this.getCashBackFrenchPromptIndexOfMAC());
		sb.append(this.getCashBackFrenchPrompt()).append(UTFUtils.FS);

		sb.append(this.getTotalOKpromptLineNumber());
		sb.append(this.getTotalOKPromptEnglish()).append(UTFUtils.FS);
		sb.append(this.getTotalOKPromptFrench()).append(UTFUtils.FS);

		sb.append(this.getSelectAccountpromptLineNumber());
		sb.append(this.getSelectAccountPromptEnglish()).append(UTFUtils.FS);
		sb.append(this.getSelectAccountPromptFrench()).append(UTFUtils.FS);

		sb.append(this.getPinEntryPromptLineNumber());
		sb.append(this.getPinEntryEnglishPromptIndexOfMAC());
		sb.append(this.getPinEntryEnglishPrompt()).append(UTFUtils.FS);
		sb.append(this.getPinEntryFrenchPromptIndexOfMAC());
		sb.append(this.getPinEntryFrenchPrompt()).append(UTFUtils.FS);

		sb.append(this.getPinErrorMessageLineNumber());
		sb.append(this.getPinErrorMessageEnglishPrompt()).append(UTFUtils.FS);
		sb.append(this.getPinErrorMessageFrenchPrompt()).append(UTFUtils.FS);

		return sb.toString();
	}

}

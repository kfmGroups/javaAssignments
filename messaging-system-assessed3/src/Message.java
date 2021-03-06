public class Message {

	private final String sender;
	private final String text;

	public Message(String sender, String text) {
		this.sender = sender;
		this.text = text;
	}

	public String getSender() {
		return sender;
	}

	public String getText() {
		return text;
	}

	public String toString() {
		return "From " + sender + ": " + text;
	}

	public String toEncrypedString() {
		return "From " + sender + ": " + Encryptor.encrypt(text);
	}
}

public class NegamaxWrapper {
	private State state;
	private int value;

	public NegamaxWrapper(State state, int value) {
		this.state = state;
		this.value = value;
	}

	public State getState() { return state; }
	public int getValue() { return value; }

	public void setState(State state) { this.state = state; }
	public void setValue(int value) { this.value = value; }
}
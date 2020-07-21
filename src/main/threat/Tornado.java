package main.threat;

/**
 * This class represents a concrete decorator tornado for whirlpool which has
 * ability to drag any ship along with him once captured.
 * 
 * @author player1
 *
 */
public class Tornado extends TornadoDecorator {

	private Whirlpool whirlpool;

	/**
	 * Constructor to initialize tornado from given whirlpool.
	 * 
	 * @param whirlpool
	 */
	public Tornado(Whirlpool whirlpool) {
		this.whirlpool = whirlpool;

	}

	@Override
	public void drag() {




	}
}
package main;

public class LeafHuffmanNode implements HuffmanNode {
	
	private int _count;
	private int _symbol;
	
	public LeafHuffmanNode(int symbol) {
		this._symbol = symbol;
	}
	
	public LeafHuffmanNode(int symbol, int count) {
		this._symbol = symbol;
		this._count = count;
	}

	@Override
	public int count() {
		return this._count;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

	@Override
	public int symbol() {
		return this._symbol;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public boolean isFull() {
		return true;
	}

	@Override
	public boolean insertSymbol(int length, int symbol) {
			throw new RuntimeException("Cannot insert symbol on a leaf node");
	}

	@Override
	public HuffmanNode left() {
			throw new RuntimeException("Cannot get left of a leaf node");
	}

	@Override
	public HuffmanNode right() {
			throw new RuntimeException("Cannot get right of a leaf node");
	}

}

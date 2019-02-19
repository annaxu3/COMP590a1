package main;

public class InternalHuffmanNode implements HuffmanNode {
	
	private HuffmanNode _left;
	private HuffmanNode _right;
	
	public InternalHuffmanNode(HuffmanNode left, HuffmanNode right) {
		this._left = left;
		this._right = right;
	}
	
	public InternalHuffmanNode() {
		this._left = null;
		this._right = null;
	}

	@Override
	public int count() {
		return this.left().count() + this.right().count();
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public int symbol() {
			throw new RuntimeException("Cannot get symbol of an internal node");
	}
	
	@Override
	public int height() {
		return Math.max(this._left.height(), this._right.height()) + 1;
	}

	@Override
	public boolean isFull() {
		if ((this._left != null) && (this._right != null) && (this._left.isFull()) && this._right.isFull()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean insertSymbol(int length, int symbol) {
		if (length == 1) {
			if (this._left == null) {
				this._left = new LeafHuffmanNode(symbol);
				return true;
			} else if (this._right == null) {
				this._right = new LeafHuffmanNode(symbol);
				return true;
			} else {
				return false;
			}
		} else if (length >= 1) {
			if (this._left== null || this._left.isFull() != true) {
				if (this._left == null) {
					this._left = new InternalHuffmanNode();
					this._left.insertSymbol(length - 1, symbol);
				} else {
					this._left.insertSymbol(length - 1, symbol);
				}
			} else if (this._right == null || this._right.isFull() != true) {
				if (this._right == null) {
					this._right = new InternalHuffmanNode();
					this._right.insertSymbol(length - 1, symbol);
				} else {
					this._right.insertSymbol(length - 1, symbol);
				}
			} 
		} 
		return false;
	}

	@Override
	public HuffmanNode left() {
		return this._left;
	}

	@Override
	public HuffmanNode right() {
		return this._right;
	}

}
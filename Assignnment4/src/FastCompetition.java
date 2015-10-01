public class FastCompetition<E> implements Competition<E> {
	private int size = 0;
	private DoubleNode<E> Head;
	private final int storageSize;

	private int index_Counter = 0;
	private E elementAtIndex = null;
	private DoubleNode<E> SortedLinkListStart = null;
	private DoubleNode<E> SortedLinkListEnd = null;
	private int sortedSize = 0;

	public FastCompetition(int i) {
		storageSize = i;
	}

	@Override
	public boolean add(E e) {
		DoubleNode<E> copyRef = this.Head;
		DoubleNode<E> newNode = new DoubleNode<E>(e);

		if (Head != null) {
			while (true) {
				if (copyRef.getDataOne().toString().compareTo(e.toString()) <= 0) {
					if (copyRef.getRight() != null) {
						copyRef = copyRef.getRight();
					} else// if(copyRef.getRight()==null)
					{
						copyRef.setRight(newNode);
						// System.out.println(copyRef.getDataOne()+" 's Right node added  "
						// + newNode.getDataOne());
						size++;
						return true;
						// break;
					}
				}

				else {
					if (copyRef.getLeft() != null) {
						copyRef = copyRef.getLeft();
					} else // if(copyRef.getLeft()==null)
					{
						copyRef.setLeft(newNode);
						// System.out.println(copyRef.getDataOne()+" Left node added  "
						// + newNode.getDataOne());
						size++;
						return true;
						// break;
					}
				}
			}
		}

		else// if(Head==null)
		{ // System.out.println("Head node added   " + newNode.getDataOne());
			size++;
			Head = newNode;
			return true;

		}

		// return false;
	}

	public boolean contains(Object o) {
		DoubleNode<E> copyRef = this.Head;
		int compare;
		if (Head == null)
			return false;

		while (true) {
			compare = copyRef.getDataOne().toString().compareTo(o.toString());
			if (compare == 0) {

				return true;
				// break;
			}

			else if (compare < 0) {
				if (copyRef.getRight() == null) {
					return false;
					// System.out.println("From"+copyRef.getDataOne()+"to Going to node"+copyRef.getRight().getDataOne());
				}

				copyRef = copyRef.getRight();

			}

			else {
				if (copyRef.getLeft() == null) {
					// System.out.println("From"+copyRef.getDataOne()+"to Going to node"+copyRef.getLeft().getDataOne());
					return false;

				}
				copyRef = copyRef.getLeft();

			}
		}
	}

	public void nodeRemover(DoubleNode<E> NodeRef) {
		if (NodeRef.getRight() != null) {
			DoubleNode<E> secondCopyRef = NodeRef.getRight();
			DoubleNode<E> tempPRef;
			DoubleNode<E> tempRef;
			if (secondCopyRef.getLeft() != null) {
				while (secondCopyRef.getLeft().getLeft() != null) {
					secondCopyRef = secondCopyRef.getLeft();
				}
				tempPRef = secondCopyRef;
				tempRef = secondCopyRef.getLeft();
				// System.out.println("Removed node General case" +
				// NodeRef.getDataOne());
				NodeRef.setDataOne(tempRef.getDataOne());
				if (NodeRef.getRight() != null) {

					tempPRef.setLeft(tempRef.getRight());
					tempRef.setRight(null);
					size--;
				} else {
					tempPRef.setLeft(null);

					size--;
				}
			} else if (secondCopyRef.getLeft() == null) {
				if (secondCopyRef.getRight() != null) {
					// System.out.println("Removed node Second node's left side null but stuff in right"
					// + NodeRef.getDataOne());
					NodeRef.setDataOne(secondCopyRef.getDataOne());
					NodeRef.setRight(secondCopyRef.getRight());
					secondCopyRef.setRight(null);
					size--;
				} else if (secondCopyRef.getRight() == null) {
					// System.out.println("Removed node Second node's left and right side is null"
					// + NodeRef.getDataOne());
					NodeRef.setDataOne(secondCopyRef.getDataOne());
					NodeRef.setRight(null);
					size--;
				}
			}
		}

	}

	public boolean remove(Object o) {
		DoubleNode<E> copyRef = this.Head;
		DoubleNode<E> copyPRef = null;
		boolean LR = true;
		if (Head != null) {
			while (true) {

				if (copyRef.getDataOne().equals(o.toString())) {
					// removing here
					// break;
					if (copyRef.getRight() != null) {
						nodeRemover(copyRef);
						return true;
					} else if (copyRef.getRight() == null) {

						if (copyRef.getLeft() != null) {
							if (copyPRef == null) {
								this.Head = copyRef.getLeft();
								return true;
							}
							// System.out.println("removed node, right is null but something in left"
							// +copyRef.getDataOne().toString());
							// System.out.println("Parent at"+copyPRef.getDataOne().toString());
							// System.out.println("Child at"+copyRef.getDataOne().toString());

							if (copyPRef != null) {
								copyPRef.setRight(copyRef.getLeft());
								copyRef.setLeft(null);
								size--;
								// return true;
							}

						} else if (copyRef.getLeft() == null) {
							if (copyPRef == null) {
								this.Head = null;
								size--;
								return true;
							}
							// System.out.println("Child"+copyRef.getDataOne());
							// System.out.println("Parent"+copyPRef.getDataOne());

							if (LR) {
								// System.out.println("Removed left node  " +
								// copyRef.getDataOne());
								copyPRef.setLeft(null);
							} else if (!LR) {
								// System.out.println("Removed right node  " +
								// copyRef.getDataOne());
								copyPRef.setRight(null);
							}
							size--;
							return true;

						}
					}
				}

				else if (copyRef.getDataOne().toString()
						.compareTo(o.toString()) < 0) {

					if (copyRef.getRight() != null) {
						copyPRef = copyRef;
						LR = false;
						copyRef = copyRef.getRight();
					} else
						return false;

				}

				else {
					// System.out.println("this is  " + copyRef.getDataOne());
					if (copyRef.getLeft() != null) {
						copyPRef = copyRef;
						LR = true;
						copyRef = copyRef.getLeft();
					} else
						return false;
				}
			}
		}

		else if (Head == null) {
			return false;

		}

		return false;
	}

	@Override
	public E elementAt(int index) {
		// TODO Auto-generated method stub
		// System.out.println(index);
		sorting(Head, index);
		// System.out.println(answer);
		return this.elementAtIndex;
	}

	@Override
	public Competition<E> sort() {
		// TODO Auto-generated method stub
		DoubleNode<E> copy = Head;
		// SortedArray= new String[size];
		sorting(copy, -1);

		// System.out.println(index_Counter+"index counter");
		index_Counter = 0;
		return this;
	}

	public void sorting(DoubleNode<E> node, int limit) {
		if (node == null) {
			return;
		} else {
			sorting(node.getLeft(), limit);
			if (limit == -1) {
				addElementtoSortedLinkList(node.getDataOne());
			}
			// System.out.println(node.getDataOne().toString());
			if (index_Counter == limit) {
				// System.out.println("I have reached here yapee");
				elementAtIndex = node.getDataOne();
				return;
			}
			index_Counter++;
			if (elementAtIndex != null)
				return;
			sorting(node.getRight(), limit);

		}

	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	public void addElementtoSortedLinkList(E obj) {
		DoubleNode<E> copyRef = this.SortedLinkListEnd;
		DoubleNode<E> newNode = new DoubleNode<E>(obj);

		if (SortedLinkListStart != null) {

			if (copyRef.getRight() == null) {
				copyRef.setRight(newNode);
				copyRef = copyRef.getRight();
				SortedLinkListEnd = copyRef;
				sortedSize++;
				// System.out.println("new nodes added from here"+
				// SortedLinkListEnd.getDataOne());
			}
		} else if (SortedLinkListStart == null) { // System.out.println("First node added");
			sortedSize++;
			SortedLinkListStart = newNode;
			SortedLinkListEnd = SortedLinkListStart;
		}

	}

}

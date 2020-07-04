package cache.framework.core;

import java.util.HashMap;
import java.util.Map;

import cache.framework.datamodels.DataNode;
import cache.framework.exceptions.CacheException;

public class LRUCache implements Cache {
	private int maxSize;
	private Map<String, DataNode> dataNodeReference;
	private DataNode dataNodeHead;
	private DataNode dataNodeTail;
	private int currentSize;
	
	
	public LRUCache(int size) {
		//assuming max size will be in range of 32 bit int size.
		this.maxSize = size;
		this.dataNodeReference = new HashMap<>();
		this.dataNodeHead = null;
		this.dataNodeTail = null;
		this.currentSize = 0;
	}
	
	@Override
	public String getData(String key) {
		
		if(!this.contains(key)) {
			return null;
		}
		DataNode data = dataNodeReference.get(key);
		moveToFront(data);
		return data.getValue();
		
	}
	
	@Override
	public void setData(String key, String value) throws CacheException{
		if(maxSize <= 0) {
			throw new CacheException("cache capacity should be greater than zero.");
		}
		if(!contains(key)) {
			if(maxSize == currentSize) {
				//limit is reached. remove delete last node.
				if(dataNodeHead == dataNodeTail) {
					dataNodeReference.remove(dataNodeTail.getKey());
					dataNodeHead = dataNodeTail = null;
				} else {
					String lastNodeKey = dataNodeTail.getKey();
					dataNodeReference.remove(lastNodeKey);
					dataNodeTail = dataNodeTail.getPrevious();
					dataNodeTail.setNext(null);
					
				}
				currentSize-=1;
			}
			DataNode tmp = new DataNode(key, value);
			dataNodeReference.put(key, tmp);
			moveToFront(tmp);
			currentSize++;
			
		} else {
			//update and move to node to head.
			DataNode dataNode = this.dataNodeReference.get(key);
			dataNode.setValue(value);
			moveToFront(dataNode);
		}
	}
	
	private void moveToFront(DataNode node) {
		if(dataNodeHead==null && dataNodeTail == null) {
			dataNodeHead = dataNodeTail = node;
		} else if(!dataNodeHead.equals(node)) {
			//check if head == value, if yes do nothing.
			//else remove link from existing node append to front of head.
			//break link of existing node.
			if(node.getPrevious()!=null) {
				node.getPrevious().setNext(node.getNext());
				if(node.getNext()!=null) {
					node.getNext().setPrevious(node.getPrevious());
				} else {
					dataNodeTail = node.getPrevious();
				}
			}
			
			DataNode oldHead = this.dataNodeHead;
			this.dataNodeHead = node;
			this.dataNodeHead.setNext(oldHead);
			this.dataNodeHead.setPrevious(null);
			oldHead.setPrevious(this.dataNodeHead);
		}
	}

	private boolean contains(String key) {
		return dataNodeReference.containsKey(key);
	}
	
	@Override
	public int getCapacity() {
		return maxSize;
	}
	
	@Override
	public int getCurrentSize() {
		return currentSize;
	}
}

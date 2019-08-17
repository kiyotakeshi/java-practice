package enumrate;

/**
 * employeeStateのデータ状態要素のenumクラス
 */
public enum IsDeleted {
	EXIST("存在",0),DELETE("削除",1);
	
	private String name;
	private Integer id;
	
	private IsDeleted(String name, Integer id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

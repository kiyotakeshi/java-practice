package enumrate;
/**
 * employeeInfoの性別要素のenumクラス
 */

public enum Sex {
	MAN("男",0), FEMALE("女",1);

	private String name;
	private Integer id;

	private Sex(String name, Integer id) {
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

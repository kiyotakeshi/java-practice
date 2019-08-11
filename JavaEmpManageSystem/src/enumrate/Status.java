package enumrate;
/**
 * employeeStatusの状態要素のenumクラス
 */

public enum Status {
	ENROLLMENT("在籍",0),RETIREMENT("退職",1),JOINEDWAIT("入社待ち",2),JOINEDCANCELLATION("入社取り消し",3);

	private String name;
	private Integer id;

	private Status(String name, Integer id) {
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

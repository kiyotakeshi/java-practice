package enumrate;
/*
 *  employee_infoの稼働状況要素のenumクラス
 */

public enum CommissioningStatus {
	NOTRUNNING("未稼働",0), RUNNING("稼働",1);

	private String name;
	private Integer id;

	private CommissioningStatus(String name, Integer id) {
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

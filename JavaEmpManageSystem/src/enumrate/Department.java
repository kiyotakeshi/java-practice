package enumrate;

public enum Department {
	DEVELOPMENT("開発",0),
	NETWORK("NW",1),
	VERIFICATION("検証",2),
	OFFICE("オフィス",3),
	MANAGEMENT("管理", 4);

	private String name;
	private Integer id;

	private Department(String name, Integer id) {
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

	public static String resultDepart(int number) {
		/*
		 *  employee_infoのdepartment番号と
		 *  enumのidが一致したらenumのnameを返す
		 */
		for(Department dpt: Department.values()) {
			if (dpt.id == number) {
				return dpt.name;
			}
		}
		return null;
	}
}

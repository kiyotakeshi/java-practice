package model;

import dao.MutterDAO;

public class PostMutterLogic {

	// 引数のMutterインスタンスをMutterテーブルに追加
	public void execute(Mutter mutter) {
		MutterDAO dao = new MutterDAO();
		dao.create(mutter);
	}
}

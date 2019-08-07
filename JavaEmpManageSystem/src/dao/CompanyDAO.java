package dao;

import java.util.ArrayList;

import dto.CompanyDTO;

public class CompanyDAO extends BaseDAO{
	public ArrayList<Company> findCompany(){

		ArrayList<Company> cmpList = new ArrayList<CompanyDTO>();
		
		try {
			con = connect();
			
			// SQLの準備
			PreparedStatement pstmt = con.prepareStatement
		
		}

	}

}

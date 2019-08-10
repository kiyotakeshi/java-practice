package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.CompanyDTO;

public class CompanyDAO extends BaseDAO{
	public ArrayList<CompanyDTO> findCompany(){

		ArrayList<CompanyDTO> cmpList = new ArrayList<CompanyDTO>();

		try {
			con = connect();

			// SQLの準備
			PreparedStatement pstmt = con.prepareStatement("select * from FinalSubject.company_info");

			// SQLの送信
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				CompanyDTO cd = new CompanyDTO();
				cd.setCompanyId(rs.getInt("company_id"));
				cd.setCompanyName(rs.getString("company_name"));
				cd.setAbbreviation(rs.getString("abbreviation"));
				cd.setIsDeleted(rs.getString("is_deleted"));
				cd.setCreated(rs.getDate("created").toLocalDate());
				cd.setModified(rs.getDate("modified").toLocalDate());
				cd.setCreatedId(rs.getString("created_id"));
				cd.setModifiedId(rs.getString("modified_id"));

				cmpList.add(cd);
			}
			rs.close();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return cmpList;
	}
}

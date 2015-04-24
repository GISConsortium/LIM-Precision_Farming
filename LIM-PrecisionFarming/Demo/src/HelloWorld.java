import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.io.Converters;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HelloWorld
 */

@WebServlet("/helloWorld.do")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloWorld() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * A simple HelloWorld Servlet
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {

		List<Parcel> Prcllist = new ArrayList<Parcel>();
		String loadType = req.getParameter("load");
		//String countryCode = "2";
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		res.setHeader("Cache-control", "no-cache, no-store");
		res.setHeader("Pragma", "no-cache");
		res.setHeader("Expires", "-1");

		res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Methods", "POST");
		res.setHeader("Access-Control-Allow-Headers", "Content-Type");
		res.setHeader("Access-Control-Max-Age", "86400");

		Gson gson = new Gson();
		JsonObject myObj = new JsonObject();

		
		if(Integer.parseInt(loadType)==2)
		{
			String ParcelStr = req.getParameter("serchTxt");
			//Parcel parcelId = getInfo(ParcelStr);
			
			Prcllist=getSerData(ParcelStr);
			JsonElement parcelObj = gson.toJsonTree(Prcllist);
			if (Prcllist.isEmpty() == true) {
				myObj.addProperty("success", false);
			} else {
				myObj.addProperty("success", true);
			}
			myObj.add("ParcelInfo", parcelObj);
			out.println(myObj.toString());
	
			out.close();
		}
		else if(Integer.parseInt(loadType)==1)
		{
			Prcllist=getAll();
			JsonElement parcelObj = gson.toJsonTree(Prcllist);
			if (Prcllist.isEmpty() == true) {
				myObj.addProperty("success", false);
			} 
			else {
				myObj.addProperty("success", true);
			}
			myObj.add("ParcelInfo", parcelObj);
			out.println(myObj.toString());
	
			out.close();
			
		}
		// postgres coonection
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		res.getWriter().write("Hello World!");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws java.io.IOException {
		doPost(req, res);
	}

	private List<Parcel> getAll() {

		List<Parcel> lstParcelInfo=new ArrayList<Parcel>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		// Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/Project_LIM", "postgres",
					"postgres");

			sql = "select id,land_owner from parcels;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Parcel parcel = new Parcel();
				//parcel.setCropType(rs.getString("crop_detai").trim());
				// parcel.setNitrogen(rs.getString("cropType").trim());
				parcel.setOwner(rs.getString("land_owner").trim());
				parcel.setParcelId(rs.getString("id").trim());
				// parcel.setPhos(rs.getString("cropType").trim());
				// parcel.setPottasium(rs.getString("cropType").trim());
				//parcel.setSoilType(rs.getString("soil_profi").trim());
				lstParcelInfo.add(parcel);
				

			}
			conn.close();

			rs.close();
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlex) {
					// ignore -- as we can't do anything about it here
				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlex) {
					// ignore -- as we can't do anything about it here
				}

				conn = null;
			}
		}

		return lstParcelInfo;

	}
	
	
	
	
	private List<Parcel> getSerData(String parcelCode) {

		List<Parcel> lstParcelInfo=new ArrayList<Parcel>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		// Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/Project_LIM", "postgres",
					"postgres");

			sql = "select * from parcels inner join \"soil_Nutr_Detail\" a on parcels.soil_profi=a.\"soil_Code\" inner join ownerinfo on ownerinfo.id=CAST(parcels.\"ownerId\" as integer) where parcels.id=? or parcels.land_owner=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,parcelCode.trim());
			stmt.setString(2,parcelCode.trim());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Parcel parcel = new Parcel();
				parcel.setCropType(rs.getString("crop_detai").trim());
				parcel.setOwner(rs.getString("land_owner").trim());
				parcel.setParcelId(rs.getString("id").trim());
				parcel.setSoilType(rs.getString("soil_profi").trim());
				parcel.setArea(Double.parseDouble(rs.getString("area_sqm").trim()));
				parcel.setLandUseType(rs.getString("landuse_ty").trim());
				parcel.setNitrogen(Double.parseDouble(rs.getString("nitrogen").trim()));
				parcel.setPhos(Double.parseDouble(rs.getString("Phos").trim()));
				parcel.setPottasium(Double.parseDouble(rs.getString("pottas").trim()));
				parcel.setAddress(rs.getString("Address").trim());
				parcel.setImage(rs.getString("imagePath").trim());
				parcel.setOnLease(Boolean.parseBoolean(rs.getString("isOnLease").trim()));
				parcel.setLeaseStart(rs.getString("LeaseFrom").trim());
				parcel.setLeaseEnd(rs.getString("LeaseTo").trim());
				lstParcelInfo.add(parcel);
				
				conn.close();
				

			}
			conn.close();

			rs.close();
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlex) {
					// ignore -- as we can't do anything about it here
				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlex) {
					// ignore -- as we can't do anything about it here
				}

				conn = null;
			}
		}

		return lstParcelInfo;

	}
	
	
	
	
	
	
	
	
	private Parcel getInfo(String parcelCode) {

		Parcel parcel = new Parcel();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;
		// Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/Project_LIM", "postgres",
					"postgres");

			sql = "select * from parcels inner join \"soil_Nutr_Detail\" a on parcels.soil_profi=a.\"soil_Code\" inner join ownerinfo on ownerinfo.id=CAST(parcels.id as integer) where parcels.id=? or parcels.land_owner=?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,parcelCode.trim());
			stmt.setString(2,parcelCode.trim());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				parcel.setCropType(rs.getString("crop_detai").trim());
				parcel.setOwner(rs.getString("land_owner").trim());
				parcel.setParcelId(rs.getString("id").trim());
				parcel.setNitrogen(Double.parseDouble(rs.getString("nitrogen").trim()));
				parcel.setPhos(Double.parseDouble(rs.getString("Phos").trim()));
				parcel.setPottasium(Double.parseDouble(rs.getString("pottas").trim()));
				parcel.setSoilType(rs.getString("soil_profi").trim());
				parcel.setArea(Double.parseDouble(rs.getString("area_sqm").trim()));
				parcel.setLandUseType(rs.getString("landuse_ty").trim());
				conn.close();

			}

			rs.close();
			stmt.close();
			stmt = null;
			conn.close();
			conn = null;

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlex) {
					// ignore -- as we can't do anything about it here
				}

				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException sqlex) {
					// ignore -- as we can't do anything about it here
				}

				conn = null;
			}
		}

		return parcel;

	}

}

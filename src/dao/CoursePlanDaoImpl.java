package dao;

import java.awt.image.DataBuffer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.CoursePlanException;
import colors.ConsoleColors;
import model_JavaBeen.CoursePlan;
import utility.DBUtil;

public class CoursePlanDaoImpl implements CoursePlanDao {
	
	@Override
	public String addCoursePlan() throws CoursePlanException {

		String msg="courseplan not added";
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter planid");
		int planId=sc.nextInt();
		
		System.out.println("enter batchId");
		int batchId=sc.nextInt();
		
		System.out.println("enter daynumber");
		int daynumber=sc.nextInt();
	
		System.out.println("enter topic");
		String topic=sc.next();
		
		System.out.println("enter status 0/1");
		int status=sc.nextInt();
		
		try(Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("insert into courseplan(planId,batchId,daynumber,topic,status) values(?,?,?,?,?)");
			
			ps.setInt(1,planId);
			ps.setInt(2,batchId);
			ps.setInt(3,daynumber );
			ps.setString(4, topic);
			ps.setInt(5, status);
			
			int rs=ps.executeUpdate();
			if(rs>0) {
				msg="courseplan added";
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String updateStatus() throws CoursePlanException {

		String msg="courseplan not updateStatus";
		Scanner sc=new Scanner(System.in);
		System.out.println("enter planid");
		int pid=sc.nextInt();
		System.out.println("enter status 0/1");
		int status=sc.nextInt();
		try(Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("update courseplan set status=? where planid=?");
			
			ps.setInt(2,pid);
			
			ps.setInt(1, status);
			
			int rs=ps.executeUpdate();
			if(rs>0) {
				msg="courseplan updateStatus";
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
		
	}

	@Override
	public String updateStatusAdmin() throws CoursePlanException {
		// TODO Auto-generated method stub
		String msg="courseplan not updateStatus";
		Scanner sc=new Scanner(System.in);
		System.out.println("enter planid");
		int pid=sc.nextInt();
		System.out.println("enter status 0/1");
		int status=sc.nextInt();
		try(Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("update courseplan set status=? where planid=?");
			
			ps.setInt(2,pid);
			
			ps.setInt(1, status);
			
			int rs=ps.executeUpdate();
			if(rs>0) {
				msg="courseplan updateStatus";
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	@Override
	public String updateTopic() throws CoursePlanException {

		String msg="courseplan not update topic";
		Scanner sc=new Scanner(System.in);
		System.out.println("enter planid");
		int pid=sc.nextInt();
		System.out.println("enter topic");
		sc.nextLine();
		String topic=sc.nextLine();
		
		try(Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("update courseplan set topic=? where planid=?");
			
			ps.setInt(2,pid);
			
			ps.setString(1, topic);
			
			int rs=ps.executeUpdate();
			if(rs>0) {
				msg="courseplan update topic";
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}



	@Override
	public List<CoursePlan> viewAllCoursePlanDateWise() throws CoursePlanException {
	List<CoursePlan> coursePlans = new ArrayList<>();
		
	
	
		try(Connection conn = DBUtil.provideConnection()){
			
			PreparedStatement ps = conn .prepareStatement("SELECT * FROM courseplan");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {		
				
				int pid = rs.getInt("planId");
				int bid = rs.getInt("batchId");
				int dNo = rs.getInt("daynumber");
				String topic = rs.getString("topic");

				Boolean staus = rs.getBoolean("status");
				
//				String dt = date.toString();
				
				CoursePlan course = new CoursePlan(pid, bid, dNo, topic, staus);
				
				coursePlans.add(course);
				
			}
			
			if(coursePlans.size() == 0)
				throw new CoursePlanException(ConsoleColors.RED_BACKGROUND+"No Plan is Created till Now.."+ConsoleColors.RESET);
			
		}catch(SQLException e) {
			throw new CoursePlanException(ConsoleColors.RED_BACKGROUND+e.getMessage()+ConsoleColors.RESET);
			
		}
		
		return coursePlans;
	}

}

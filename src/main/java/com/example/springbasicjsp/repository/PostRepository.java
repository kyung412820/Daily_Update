package com.example.springbasicjsp.repository;

import com.example.springbasicjsp.data.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;


@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 작성자 정보 저장
    public void savePostUser(Post post) {
        String sql = "INSERT INTO postuser (id, name, email, origindate, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getId(), post.getName(), post.getEmail(), post.getOrigindate(), post.getDate());
    }

    // 작업 정보 저장
    public void savePostWork(Post post) {
        String sql = "INSERT INTO postwork (work, name, password, date, user_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, post.getWork(), post.getName(), post.getPassword(), post.getDate(), post.getUserId());
    }


    public List<Post> findAllPosts() {
        String sql = "SELECT pu.id, pu.name, pu.email, pu.origindate, pu.date, " +
                "pw.work, pw.password, pw.user_id, pw.date AS work_date " +
                "FROM postuser pu " +
                "LEFT JOIN postwork pw ON pu.id = pw.user_id " +
                "ORDER BY pu.date DESC";


        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> Post.builder()
                .id(rs.getLong("id"))                      // postuser.id
                .name(rs.getString("name"))                // 작성자 이름
                .email(rs.getString("email"))              // 작성자 이메일
                .origindate(rs.getString("origindate"))    // 작성일
                .date(rs.getString("date"))                // 수정일 (postuser.date)
                .work(rs.getString("work"))                // 할일 (postwork.work)
                .password(rs.getObject("password") != null ? rs.getLong("password") : null) // 비밀번호
                .userId(rs.getObject("user_id") != null ? rs.getLong("user_id") : null)     // user_id
                .build());
    }

    // 작성자 데이터 조회
    public Optional<Post> findPostUserById(Long id) {
        String sql = "SELECT * FROM postuser WHERE id = ?";
        List<Post> users = jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> Post.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .origindate(rs.getString("origindate"))
                .date(rs.getString("date"))
                .build(), id);
        return users.stream().findFirst();
    }

    public List<Post> findPostSearch(Long id, String date) {
        String sql = "SELECT pu.id, pu.name, pu.email, pu.origindate, pu.date, " +
                "pw.work, pw.password, pw.user_id, pw.date AS work_date " +
                "FROM postuser pu " +
                "LEFT JOIN postwork pw ON pu.id = pw.user_id " +
                "WHERE 1=1";

        List<Object> params = new ArrayList<>();

        if (id != null) {
            sql += " AND pu.id = ?"; // postuser 테이블의 id 컬럼
            params.add(id);
        }

        if (date != null) {
            sql += " AND pu.date = ?"; // postuser 테이블의 date 컬럼
            params.add(date);
        }

        System.out.println("Generated SQL: " + sql);
        System.out.println("Parameters: " + params);

        return jdbcTemplate.query(sql, params.toArray(), (ResultSet rs, int rowNum) -> Post.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .origindate(rs.getString("origindate"))
                .date(rs.getString("date")) // 수정일 (postuser.date)
                .work(rs.getString("work")) // 할일 (postwork.work)
                .password(rs.getObject("password") != null ? rs.getLong("password") : null)
                .userId(rs.getObject("user_id") != null ? rs.getLong("user_id") : null)
                .build());
    }



    // 작성자 데이터 수정
    public int updatePostUser(Post post) {
        String sql = "UPDATE postuser SET name = ?, email = ?, origindate = ?, date = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, post.getName(), post.getEmail(), post.getOrigindate(), post.getDate(), post.getId());
        System.out.println("Rows affected in postuser: " + rowsAffected);
        return rowsAffected;
    }

    public void upsertPostWork(Post post) {
        String selectSql = "SELECT COUNT(*) FROM postwork WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(selectSql, Integer.class, post.getUserId());

        if (count != null && count > 0) {
            // 데이터가 있으면 UPDATE
            String updateSql = "UPDATE postwork SET work = ?, name = ?, password = ?, date = ? WHERE user_id = ?";
            int rowsAffected = jdbcTemplate.update(updateSql, post.getWork(), post.getName(), post.getPassword(), post.getDate(), post.getUserId());
            System.out.println("Rows affected in postwork (UPDATE): " + rowsAffected);
        } else {
            // 데이터가 없으면 INSERT
            String insertSql = "INSERT INTO postwork (work, name, password, date, user_id) VALUES (?, ?, ?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(insertSql, post.getWork(), post.getName(), post.getPassword(), post.getDate(), post.getUserId());
            System.out.println("Rows affected in postwork (INSERT): " + rowsAffected);
        }
    }



    // 작성자 데이터 삭제
    public int deletePostUserById(Long id) {
        String sql = "DELETE FROM postuser WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // 작업 데이터 삭제
    public int deletePostWorkByUserId(Long password) {
        String sql = "DELETE FROM postwork WHERE user_id = ?";
        return jdbcTemplate.update(sql, password);
    }
}

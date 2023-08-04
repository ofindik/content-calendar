package dev.osmanfindik.contentcalendar.repository;

import dev.osmanfindik.contentcalendar.model.Content;
import dev.osmanfindik.contentcalendar.model.Status;
import dev.osmanfindik.contentcalendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContentJdbcTemplateRepository {
	private final JdbcTemplate jdbcTemplate;

	public ContentJdbcTemplateRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Content> getAllContent () {
		String sql = "select * from Content";
		List<Content> contentList = jdbcTemplate.query (sql, ContentJdbcTemplateRepository::mapRow);
		return contentList;
	}

	public void defineContent (String title, String description, String status, String content_type, String url) {
		String sql = "insert into Content (title, description, status, content_type, date_created, url) values (?, ?, ?, ?, NOW(), ?)";
		jdbcTemplate.update (sql, title, description, status, content_type, url);
	}

	public void updateContent (int id, String title, String description, String status, String content_type, String url) {
		String sql = "update Content set title=?, description=?, status=?, content_type=?, date_updated=NOW(), url=? where id=?)";
		jdbcTemplate.update (sql, title, description, status, content_type, url, id);
	}

	public void deleteContent (int id) {
		String sql = "delete from Content where id=?";
		jdbcTemplate.update (sql, id);
	}

	public Content getContent (int id) {
		String sql = "select * from Content where id=?";
		Content content = jdbcTemplate.queryForObject (sql, new Object[] { id }, ContentJdbcTemplateRepository::mapRow);
		return content;
	}

	private static Content mapRow (ResultSet resultSet, int rowNum) throws SQLException {
		return new Content (resultSet.getInt ("id"),
				resultSet.getString ("title"),
				resultSet.getString ("description"),
				Status.valueOf (resultSet.getString ("status")),
				Type.valueOf (resultSet.getString ("content_type")),
				resultSet.getTimestamp ("date_created").toLocalDateTime (),
				(null != resultSet.getTimestamp ("date_updated")) ? resultSet.getTimestamp ("date_updated").toLocalDateTime () : null,
				resultSet.getString ("url"));
	}
}

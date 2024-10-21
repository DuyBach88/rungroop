INSERT INTO clubs (title, photo_url, content, created_on, updated_on)
VALUES
    ('Club A', 'http://example.com/photoA.jpg', 'Content for Club A', CURRENT_DATE, CURRENT_DATE),
    ('Club B', 'http://example.com/photoB.jpg', 'Content for Club B', CURRENT_DATE, CURRENT_DATE),
    ('Club C', 'http://example.com/photoC.jpg', 'Content for Club C', CURRENT_DATE, CURRENT_DATE);

DELETE FROM clubs WHERE title ="Club B" ; -- Thay '1' bằng ID của câu lạc bộ bạn muốn xóa

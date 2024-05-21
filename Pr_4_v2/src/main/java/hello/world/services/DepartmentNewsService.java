package hello.world.services;

import hello.world.models.DepartmentNews;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DepartmentNewsService {

 /*   private final List<DepartmentNews> newsList = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();


    public void addNews(DepartmentNews news) {
        news.setId(counter.incrementAndGet());
        newsList.add(news);
    }


    public void deleteNews(Long id) {
        newsList.removeIf(news -> news.getId().equals(id));
    }


    public List<DepartmentNews> listNews() {
        return newsList;
    }


    public List<DepartmentNews> getNewsByDepartmentId(Long departmentId) {
        List<DepartmentNews> departmentNews = new ArrayList<>();
        for (DepartmentNews news : newsList) {
            if (news.getDepartmentId().equals(departmentId)) {
                departmentNews.add(news);
            }
        }
        return departmentNews;
    }*/
}

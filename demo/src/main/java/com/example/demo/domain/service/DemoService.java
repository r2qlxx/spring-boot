package com.example.demo.domain.service;

import com.example.demo.domain.common.*;
import com.example.demo.domain.exception.SystemException;
import com.example.demo.domain.object.CacheKey;
import com.example.demo.domain.object.Post;
import com.example.demo.domain.object.User;
import com.example.demo.domain.object.UserRole;
import com.example.demo.domain.repository.RdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {
    private final HttpHandler http;
    private final RdsRepository rds;
    private final Asyncer asyncer;
    private final Cacher cacher;
    private final ProfileIf profile;
    private final Retryer retryer;

    // -------------------
    // -- Http Requests --
    // -------------------
    public void http() {
        ResponseEntity<Post> get = http.get(1);
        ResponseEntity<Post> post = http.post(new Post(1, "Demo Post", "Demo Post Body", 1));
        ResponseEntity<Post> put = http.put(1, new Post(1, "Demo Put", "Demo Put Body", 2));
        ResponseEntity<Post> patch = http.patch(1, Map.of("title", "Demo Patch Title", "body", "Demo Patch Body"));
        ResponseEntity<Post> delete = http.delete(1);
        log.info(get.toString());
        log.info(post.toString());
        log.info(put.toString());
        log.info(patch.toString());
        log.info(delete.toString());
    }

    // ----------
    // -- CRUD --
    // ----------
    public void crud() {
        // User1 has already created.

        int rowsAffected_create = rds.create(new User(2, "user2",
                "pass2", LocalDate.parse("0002-02-02"), UserRole.USER));
        log.info(Integer.toString(rowsAffected_create));    // 1

        Optional<User> user = rds.read(1);
        log.info(user.toString());
        // Optional[User(userid=1, username=user1, password=pass1, birthday=0001-01-01, role=ADMIN)]

        List<User> demoUsers = rds.readAll();
        log.info(demoUsers.toString());
        // [User(userid=1, username=user1, password=pass1, birthday=0001-01-01, role=ADMIN),
        //  User(userid=2, username=user2, password=pass2, birthday=0002-02-02, role=USER)]

        int rowsAffected_update = rds.update(new User(2, "user3",
                "pass3", LocalDate.parse("0003-03-03"), UserRole.USER));
        log.info(Integer.toString(rowsAffected_update));   // 1

        int rowAffected_delete = rds.delete(2);
        log.info(Integer.toString(rowAffected_delete));   // 1
    }

    // ----------------------------
    // -- GlobalControllerAdvice --
    // ----------------------------
    public void error() throws SystemException {
        try {
            // Intentional Error
            int a = 1 / 0;
        } catch (Throwable e) {
            throw new SystemException(e, "ERROR_001", new Object[]{"arg1", "arg2"});
        }
    }

    // ---------
    // -- AOP --
    // ---------
    public void before() {
        log.info("DemoService before");
    }

    public void after() {
        log.info("DemoService after");
    }

    public void afterReturning() {
        log.info("DemoService afterReturning");
    }

    public void afterThrowing() {
        try {
            throw new RuntimeException();
        } catch (Exception e) {
            log.info("DemoService afterThrowing");
            throw e;
        }
    }

    public void around() {
        log.info("DemoService around");
    }

    // -----------
    // -- Async --
    // -----------
    public void async() {
        log.info("Async Before");
        asyncer.async();
        log.info("Async After");

        // Log
        // 2025-04-14T14:46:52.840+09:00  INFO 11116 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : Async Before
        // 2025-04-14T14:46:52.848+09:00  INFO 11116 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : Async After
        // 2025-04-14T14:46:52.849+09:00  INFO 11116 --- [demo] [         task-1] com.example.demo.domain.common.Asyncer   : heavy task before
        // 2025-04-14T14:46:57.856+09:00  INFO 11116 --- [demo] [         task-1] com.example.demo.domain.common.Asyncer   : heavy task after
    }

    // -----------
    // -- Cache --
    // -----------
    public void cache() {
        long start_ms = System.currentTimeMillis();
        cacher.cache(new CacheKey("Key1"));
        log.info("Key1 ProcTime: " + (System.currentTimeMillis() - start_ms));

        start_ms = System.currentTimeMillis();
        cacher.cache(new CacheKey("Key2"));
        log.info("Key2 ProcTime: " + (System.currentTimeMillis() - start_ms));

        start_ms = System.currentTimeMillis();
        cacher.cache(new CacheKey("Key1"));
        log.info("Key1(Cached) ProcTime: " + (System.currentTimeMillis() - start_ms));

        // Log
        // 2025-04-14T14:57:00.772+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task started.
        // 2025-04-14T14:57:03.784+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task ended.
        // 2025-04-14T14:57:03.786+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : Key1 ProcTime: 3038
        // 2025-04-14T14:57:03.786+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task started.
        // 2025-04-14T14:57:06.801+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task ended.
        // 2025-04-14T14:57:06.801+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : Key2 ProcTime: 3015
        // 2025-04-14T14:57:06.802+09:00  INFO 6488 --- [demo] [nio-8080-exec-1] c.e.demo.domain.service.DemoService      : Key1(Cached) ProcTime: 1
    }

    // -------------
    // -- Profile --
    // -------------
    public void profile() {
        String result = profile.doSomething();
        log.info(result);

        // if spring.profiles.active=dev -> "Dev Profile" on console
        // if spring.profiles.active=prod -> "Prod Profile" on console
    }

    // -----------
    // -- Retry --
    // -----------
    public void retry() {
        try {
            retryer.retry();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Log
        // 2025-04-14T14:48:24.481+09:00  INFO 14952 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : retry
        // 2025-04-14T14:48:25.487+09:00  INFO 14952 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : retry
        // 2025-04-14T14:48:26.502+09:00  INFO 14952 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : retry
        // 2025-04-14T14:48:26.503+09:00  INFO 14952 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : recover
    }

    // ---------------------------------
    // -- Sample Method For Unit Test --
    // ---------------------------------
    public String doSomething() {
        return "Demo Service";
    }

    public int createNewUser(User user) {
        return rds.create(user);
    }

    public Optional<User> readUser(int id) {
        return rds.read(id);
    }

    public void emailUserInfo(int id) {
        readUser(id);
        // Process to email to user.
    }
}

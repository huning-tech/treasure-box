package tech.huning.treasure.box.async;

import tech.huning.treasure.box.async.dto.VideoInfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.huning.treasure.box.async.core.AsyncScheduler;
import tech.huning.treasure.box.async.specs.*;

import java.util.stream.Collectors;

/**
 * 异步调度器测试
 *
 * <p>更多内容参看<a href="https://huning.tech"><b>胡宁Tech</b></a>
 * @author huning
 * @version 1.0
 */
public class AsyncSchedulerTest {

    private static final Logger logger = LoggerFactory.getLogger(AsyncSchedulerTest.class);
    private static final IAsyncTopic TOPIC_VIDEO_TRANSFER = () -> "topic-video-transfer";

    @Test
    public void test() throws Exception {
        // 启动任务异步调度服务器(从任务队列消费任务)
        startServer();

        // 启动待处理任务及处理中任务列表打印线程
        printStockAndHandling();

        // 启动任务异步处理客户端(添加任务到队列)
        startClient();

        // 监控任务是否处理结束
        while(!isReady2Shutdown()) {
            Thread.sleep(500);
        }

        // 关闭任务异步调度器
        stopServer();
    }

    /**
     * 服务端启动
     * @throws Exception 执行异常
     */
    private void startServer() throws Exception {
        AsyncScheduler.getInstance().getServer(new IAsyncConfig() {
            @Override
            public int getTaskCapacity() {
                return 10;
            }
            public IAsyncTopic getTopic() {
                return TOPIC_VIDEO_TRANSFER;
            }
        }).register((IAsyncExecutor<VideoInfo>) task -> {
            logger.debug("task process:{}", task.getData().getTitle());
            Thread.sleep(task.getData().getDuration());
        }).register((IAsyncOverflowHandler) task -> {
            VideoInfo videoInfo = (VideoInfo)task.getData();
            logger.debug("task overflow:{}", videoInfo.getTitle());
        }).listen();
    }

    /**
     * 服务端关闭
     * @throws Exception 执行异常
     */
    private void stopServer() throws Exception {
        AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER).shutdown();
    }

    /**
     * 客户端启动
     * @throws Exception 执行异常
     */
    private void startClient() throws Exception {
        for(long i = 1; i <= 10; i++) {
            final String taskId = String.valueOf(i);
            final String title = "video" + i;
            final long duration = (long)(Math.random() * 10000);
            AsyncScheduler.getInstance().getClient(TOPIC_VIDEO_TRANSFER).schedule(new IAsyncTask<VideoInfo>() {
                @Override
                public String getId() {
                    return taskId;
                }
                public VideoInfo getData() {
                    return new VideoInfo(title, duration);
                }
            });
        }
    }

    /**
     * 启动待处理任务及处理中任务列表打印线程
     */
    private void printStockAndHandling() {
        Thread t = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(1000);
                    logger.info("----------print----------stock----------");
                    IAsyncStock stock = AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER).getStock();
                    logger.info("stock total:{}", stock.getTotal());
                    if(stock.getTotal() > 0) {
                        String ids = stock.getList().stream()
                                                    .map(IAsyncTask::getId)
                                                    .collect(Collectors.joining(","));
                        logger.info("task ids:{}", ids);
                    }

                    logger.info("----------print----------handling-------");
                    IAsyncHandling handling = AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER).getHandling();
                    logger.info("handling total:{}", handling.getTotal());
                    if(handling.getTotal() > 0) {
                        String ids = handling.getList().stream()
                                .map(IAsyncTask::getId)
                                .collect(Collectors.joining(","));
                        logger.info("handling ids:{}", ids);
                    }
                } catch (Exception e) {
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }

    /**
     * 监控任务是否处理结束
     * @return 任务是否处理结束
     */
    public boolean isReady2Shutdown() {
        try {
            IAsyncQueue asyncQueue = AsyncScheduler.getInstance().getServer(TOPIC_VIDEO_TRANSFER);
            int handlingTotal = asyncQueue.getHandling().getTotal();
            int stockTotal = asyncQueue.getStock().getTotal();
            return handlingTotal + stockTotal == 0;
        }catch (Exception e) {
        }
        return false;
    }

}

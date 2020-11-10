package java.base.a14lock.ConcurrentSkipListMap;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class EventWindowSort {
    //我们希望使用eventTime字段对事件进行排序。为了使用ConcurrentSkipListMap实现这一点，我们需要在创建比较器的实例时向其构造函数传递比较器：
    ConcurrentSkipListMap<ZonedDateTime, String> events
            = new ConcurrentSkipListMap<>(
            Comparator.comparingLong(v -> v.toInstant().toEpochMilli()));
    //我们将使用时间戳比较所有到达的事件。我们正在使用comparingLong（）方法并传递提取函数，该函数可以从ZonedDateTime获取长时间戳。

    //当我们的事件到达时，我们只需要使用put()方法将它们添加到映射中。注意，此方法不需要任何显式同步：
    public void acceptEvent(Event event) {
        events.put(event.getEventTime(), event.getContent());
    }

    //ConcurrentSkipListMap将使用在构造函数中传递给它的比较器来处理下面这些事件的排序。
    //ConcurrentSkipListMap最显著的优点是可以以无锁方式生成其数据的不可变快照的方法。
    //要获取在过去一分钟内到达的所有事件，我们可以使用tailMap()方法并传递获取元素的时间：
    //它将返回过去一分钟的所有事件。这将是一个不可变的快照，最重要的是，其他编写线程可以向ConcurrentSkipListMap添加新事件，而无需执行显式锁定。
    public ConcurrentNavigableMap<ZonedDateTime, String> getEventsFromLastMinute() {
        return events.tailMap(ZonedDateTime.now().minusMinutes(1));
    }

    //现在，我们可以使用headMap（）方法获取一分钟后到达的所有事件：
    //这将返回超过一分钟的所有事件的不可变快照。以上所有方法都属于EventWindowSort类，我们将在下一节中使用它。
    public ConcurrentNavigableMap<ZonedDateTime, String> getEventsOlderThatOneMinute() {
        return events.headMap(ZonedDateTime.now().minusMinutes(1));
    }
}

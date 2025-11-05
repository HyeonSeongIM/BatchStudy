# BatchStudy
<스케줄링 시>

### 스케줄링 핵심요소

핵심 컴포넌트 ScheduledAnnotationBeanPostProcessor <br>
1. @EnableScheduling <br>
스프링은 내부적으로 ScheduledAnnotationBeanPostProcessor 라는 특별한 빈을 등록 <br>

2. Bean Post Processor <br>
스프링 컨테이너가 모든 빈을 초기화하는 과정에 개입 <br>
ScheduledAnnotationBeanPostProcessor가 해당 빈을 검사 <br>
빈의 모든 메서드를 스캔하여 스케줄 어노테이션이 붙어있는지 확인 <br>

### 등록 컴포넌트 TaskScheduler
비서 (BeanPostProcessor) 가 스케줄 어노티에션 메서드를 찾으면 그 메서드를 실행할 수 있는 Runnable 로 감쌈<br>
cron, fixedRate, fixedDelay 같은 스케줄링 정보를 파싱하여 "트리거" 객체를 만듬<br>
비서는 이 Runnable과 트리거를 한 세트로 묶어, 스프링에 등록된 TaskScheduler에 전달하며 실행 등록<br>
별도로 설정하지 않으면 스프링은 ThreadPoolTaskScheduler 사용함<br>

### 실행 및 스레딩
ThreadPoolTaskScheuduler 는 기본적으로 단 하나의 스레드를 가진 스레드 풀을 생성 <br>
이 유일한 스레드가 스케줄 표를 계속 확인 <br>
실행 시간이 된 작업을 발견하면 해당 작업 실행 <br>
만약 태스크가 10초가 걸리는 작업이면 이 단일 스레드는 묶여있게 됨 <br>
그 10초 사이에 실행됐어야할 다른 스케줄 작업이 있다면 계속 대기해야함 <br>


### 문제가 발생할 수 있는 시나리오
1. Task의 조용한 죽음<br>
예외처리가 제대로 되지 않았을 때, 작업을 스케줄러에서 조용히 제거함<br>
-> 어떤 예외가 발생하든 절대 밖으로 던져지지 않도록 막아야함<br>

2, 작업 겹침<br>
fixedDelay는 ㄱㅊ긴 한데<br>
fixedRate는 작업이 겹칠 수도 있음<br>

3. 분산환경<br>
스케줄 어노테이션은 다른 서버를 전혀 신경쓰지 않는다.<br>




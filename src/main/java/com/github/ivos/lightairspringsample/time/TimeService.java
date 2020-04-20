package com.github.ivos.lightairspringsample.time;

import com.github.ivos.lightairspringsample.config.Logged;
import org.springframework.stereotype.Service;

import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Logged
public class TimeService implements ClockProvider {

	private static Clock clock;

	static {
		setSystemTime();
	}

	static void setFixedTime(LocalDateTime time) {
		clock = Clock.fixed(time.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
	}

	static void setSystemTime() {
		clock = Clock.systemDefaultZone();
	}

	public static LocalDateTime now() {
		return LocalDateTime.now(clock);
	}

	@Override
	public Clock getClock() {
		return clock;
	}
}

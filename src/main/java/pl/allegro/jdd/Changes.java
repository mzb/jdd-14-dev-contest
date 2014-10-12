package pl.allegro.jdd;

import com.google.common.collect.ImmutableSet;

import java.util.Collection;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class Changes {
    private final Set<Employee> fired;
    private final Set<Employee> hired;
    private final Set<Employee> changedSalary;

    public Changes(Collection<Employee> fired, Collection<Employee> hired, Collection<Employee> changedSalary) {
        checkNotNull(fired);
        checkNotNull(hired);
        checkNotNull(changedSalary);

        this.fired = ImmutableSet.copyOf(fired);
        this.hired = ImmutableSet.copyOf(hired);
        this.changedSalary = ImmutableSet.copyOf(changedSalary);
    }

    public Set<Employee> getFired() {
        return fired;
    }

    public Set<Employee> getHired() {
        return hired;
    }

    public Set<Employee> getChangedSalary() {
        return changedSalary;
    }
}

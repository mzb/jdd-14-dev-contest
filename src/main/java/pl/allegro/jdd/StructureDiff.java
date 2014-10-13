package pl.allegro.jdd;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.TreeTraverser;

public class StructureDiff {
    public Changes calculate(Employee oldCTO, Employee newCTO){
        checkNotNull(oldCTO);
        checkNotNull(newCTO);

        final TreeTraverser<Employee> traverser = new TreeTraverser<Employee>() {
            @Override
            public Iterable<Employee> children(final Employee e) {
                return e.getSubordinates();
            }
        };

        final FluentIterable<Employee> oldEmployees = traverser.preOrderTraversal(oldCTO);
        final FluentIterable<Employee> newEmployees = traverser.preOrderTraversal(newCTO);
        final MapDifference<Employee, Integer> diff = Maps.difference(
                oldEmployees.toMap(Employee::getSalary),
                newEmployees.toMap(Employee::getSalary));
        final Set<Employee> fired = diff.entriesOnlyOnLeft().keySet();
        final Set<Employee> hired = diff.entriesOnlyOnRight().keySet();
        final Set<Employee> changedSalary = diff.entriesDiffering().keySet();

        return new Changes(fired, hired, changedSalary);
    }
}

package com.qualityrailway.qr.boundary;

import com.simibubi.create.Create;
import com.simibubi.create.content.trains.graph.*;
import com.simibubi.create.content.trains.signal.SignalEdgeGroup;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.Pair;
import java.util.*;

public class ChinaHighspeedSignalPropagator {

    public static void onSignalRemoved(TrackGraph graph, ChinaHighspeedSignalBoundary signal) {
        signal.sidesToUpdate.map($ -> false);
        for (boolean front : Iterate.trueAndFalse) {
            if (signal.sidesToUpdate.get(front))
                continue;
            UUID id = signal.groups.get(front);
            if (Create.RAILWAYS.signalEdgeGroups.remove(id) != null)
                Create.RAILWAYS.sync.edgeGroupRemoved(id);

            walkSignals(graph, signal, front, pair -> {
                TrackNode node1 = pair.getFirst();
                ChinaHighspeedSignalBoundary boundary = pair.getSecond();
                boundary.queueUpdate(node1);
                return false;
            }, signalData -> {
                if (!signalData.hasSignalBoundaries()) {
                    signalData.setSingleSignalGroup(graph, EdgeData.passiveGroup);
                    return true;
                }
                return false;
            }, false);
        }
    }

    public static Map<UUID, Boolean> collectChainedSignals(TrackGraph graph, ChinaHighspeedSignalBoundary signal, boolean front) {
        HashMap<UUID, Boolean> map = new HashMap<>();
        walkSignals(graph, signal, front, pair -> {
            ChinaHighspeedSignalBoundary boundary = pair.getSecond();
            map.put(boundary.id, !boundary.isPrimary(pair.getFirst()));
            return false;
        }, com.google.common.base.Predicates.alwaysFalse(), true);
        return map;
    }

    public static void propagateSignalGroup(TrackGraph graph, ChinaHighspeedSignalBoundary signal, boolean front) {
        Map<UUID, SignalEdgeGroup> globalGroups = Create.RAILWAYS.signalEdgeGroups;
        TrackGraphSync sync = Create.RAILWAYS.sync;

        SignalEdgeGroup group = new SignalEdgeGroup(UUID.randomUUID());
        UUID groupId = group.id;
        globalGroups.put(groupId, group);
        signal.setGroup(front, groupId);
        sync.pointAdded(graph, signal);

        walkSignals(graph, signal, front, pair -> {
            TrackNode node1 = pair.getFirst();
            ChinaHighspeedSignalBoundary boundary = pair.getSecond();
            UUID currentGroup = boundary.getGroup(node1);
            if (currentGroup != null)
                if (globalGroups.remove(currentGroup) != null)
                    sync.edgeGroupRemoved(currentGroup);
            boundary.setGroupAndUpdate(node1, groupId);
            sync.pointAdded(graph, boundary);
            return true;
        }, signalData -> {
            UUID singleSignalGroup = signalData.getSingleSignalGroup();
            if (singleSignalGroup != null)
                if (globalGroups.remove(singleSignalGroup) != null)
                    sync.edgeGroupRemoved(singleSignalGroup);
            signalData.setSingleSignalGroup(graph, groupId);
            return true;
        }, false);

        group.resolveColor();
        sync.edgeGroupCreated(groupId, group.color);
    }

    private static void walkSignals(TrackGraph graph, ChinaHighspeedSignalBoundary signal, boolean front,
                                    java.util.function.Predicate<Pair<TrackNode, ChinaHighspeedSignalBoundary>> boundaryCallback,
                                    com.google.common.base.Predicate<EdgeData> nonBoundaryCallback,
                                    boolean forCollection) {
        // Implementation similar to SignalPropagator
        // This is a simplified version - you may need to adapt from Create's SignalPropagator
    }
}
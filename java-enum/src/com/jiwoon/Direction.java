package com.jiwoon;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public static Direction rotate(Direction dir) {
        switch (dir) {
            case NORTH: return EAST;
            case EAST:  return SOUTH;
            case SOUTH: return WEST;
            case WEST:  return NORTH;
        }
        throw new AssertionError("알 수 없는 방향: " + dir);
    }
}

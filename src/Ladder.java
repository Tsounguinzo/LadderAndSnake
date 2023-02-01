/*
 * Copyright (c) 2022 Beaudelaire Tsoungui Nzodoumkouo. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under My consent.
 *
 * This code is shared on GitHub in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY OF FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Please contact Me at +1 438 509 3906
 * or LinkedIn: https://www.linkedin.com/in/beaudelaire-tsoungui-nzodoumkouo-809744231
 * if you need additional information or have any questions.
 */

/**
 * Ladder class represents a Ladder in a game.
 *
 * @author Beaudelair Tsoungui Nzodoumkouo
 * @version 1.0
 */
public class Ladder {
    private final int start;
    private final int end;

    /**
     * Construct a new ladder with the given beginning and ending index
     *
     * @param start the beginning of the ladder
     * @param end the end of the ladder
     */
    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return the start point of this ladder
     */
    public int getStart(){
        return this.start;
    }

    /**
     * @return the end point of this ladder
     */
    public int getEnd() {
        return end;
    }
}

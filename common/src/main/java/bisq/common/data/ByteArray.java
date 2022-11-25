/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.common.data;

import bisq.common.encoding.Hex;
import bisq.common.proto.Proto;
import com.google.protobuf.ByteString;
import lombok.Getter;

import java.util.Arrays;

@SuppressWarnings("ClassCanBeRecord")
@Getter
public class ByteArray implements Proto {
    private final byte[] bytes;

    public ByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    public bisq.common.protobuf.ByteArray toProto() {
        return bisq.common.protobuf.ByteArray.newBuilder().setBytes(ByteString.copyFrom(bytes)).build();
    }

    public static ByteArray fromProto(bisq.common.protobuf.ByteArray proto) {
        return new ByteArray(proto.getBytes().toByteArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteArray byteArray = (ByteArray) o;
        return Arrays.equals(bytes, byteArray.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    public String toString() {
        return Hex.encode(bytes);
    }
}

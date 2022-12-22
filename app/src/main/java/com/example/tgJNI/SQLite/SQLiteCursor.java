/*
 * This is the source code of Telegram for Android v. 5.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2018.
 */

package com.example.tgJNI.SQLite;
import com.example.tgJNI.tgnet.NativeByteBuffer;

public class SQLiteCursor {
	public static final int FIELD_TYPE_INT = 1;
	public static final int FIELD_TYPE_FLOAT = 2;
	public static final int FIELD_TYPE_STRING = 3;
	public static final int FIELD_TYPE_BYTEARRAY = 4;
	public static final int FIELD_TYPE_NULL = 5;
	public boolean next() throws SQLiteException {
		int res = preparedStatement.step(preparedStatement.getStatementHandle());
		if (res == -1) {
			int repeatCount = 6;
			while (repeatCount-- != 0) {
				try {

					Thread.sleep(500);
					res = preparedStatement.step();
					if (res == 0) {
						break;
					}
				} catch (Exception e) {
				}
			}
			if (res == -1) {
				throw new SQLiteException("sqlite busy");
			}
		}
		inRow = (res == 0);
		return inRow;
	}
	private SQLitePreparedStatement preparedStatement;
	private boolean inRow = false;

	public SQLiteCursor(SQLitePreparedStatement stmt) {
		preparedStatement = stmt;
	}
	public void dispose() {
		preparedStatement.dispose();
	}
	public long longValue(int columnIndex) throws SQLiteException {
		return columnLongValue(preparedStatement.getStatementHandle(), columnIndex);
	}
	public NativeByteBuffer byteBufferValue(int columnIndex) throws SQLiteException {
		long ptr = columnByteBufferValue(preparedStatement.getStatementHandle(), columnIndex);
		if (ptr != 0) {
			return NativeByteBuffer.wrap(ptr);
		}
		return null;
	}

	native long columnLongValue(long statementHandle, int columnIndex);

	native long columnByteBufferValue(long statementHandle, int columnIndex);
}

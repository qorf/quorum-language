#include "DialogControl.h"
#include "DialogProvider.h"
#include "ControlTImpl.h"

DialogControl::DialogControl(JNIEnv* env, std::wstring&& name, std::wstring&& description, jobject jItem) : ControlT(env, std::move(name), std::move(description), jItem)
{
}


void DialogControl::Close() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		env->CallObjectMethod(javaItem, JavaClass_Dialog.Hide);
	}
}

bool DialogControl::IsModal() {
	JNIEnv* env = GetJNIEnv();
	if (env != NULL && javaItem != nullptr)
	{
		jboolean b = reinterpret_cast<jboolean>(env->CallObjectMethod(javaItem, JavaClass_Dialog.IsModal));
		bool result = b;
		return result;
	}
	return false;
}
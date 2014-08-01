#include "com_ppsea_cocosjava_WindowsAdapter.h"
#include "AppDelegate.h"
#include "cocos2d.h"
#define  GLFW_EXPOSE_NATIVE_WIN32
#define  GLFW_EXPOSE_NATIVE_WGL
#include "glfw3native.h"
#include <string>
#include <vector>
USING_NS_CC;
using namespace std;
using namespace cocos2d;
static AppDelegate* s_delegate;

//////////////////////////////////////////////////////////////////////////
// Local function
//////////////////////////////////////////////////////////////////////////
static void PVRFrameEnableControlWindow(bool bEnable)
{
	HKEY hKey = 0;

	// Open PVRFrame control key, if not exist create it.
	if(ERROR_SUCCESS != RegCreateKeyExW(HKEY_CURRENT_USER,
		L"Software\\Imagination Technologies\\PVRVFRame\\STARTUP\\",
		0,
		0,
		REG_OPTION_NON_VOLATILE,
		KEY_ALL_ACCESS,
		0,
		&hKey,
		NULL))
	{
		return;
	}

	const WCHAR* wszValue = L"hide_gui";
	const WCHAR* wszNewData = (bEnable) ? L"NO" : L"YES";
	WCHAR wszOldData[256] = {0};
	DWORD   dwSize = sizeof(wszOldData);
	LSTATUS status = RegQueryValueExW(hKey, wszValue, 0, NULL, (LPBYTE)wszOldData, &dwSize);
	if (ERROR_FILE_NOT_FOUND == status              // the key not exist
		|| (ERROR_SUCCESS == status                 // or the hide_gui value is exist
		&& 0 != wcscmp(wszNewData, wszOldData)))    // but new data and old data not equal
	{
		dwSize = sizeof(WCHAR) * (wcslen(wszNewData) + 1);
		RegSetValueEx(hKey, wszValue, 0, REG_SZ, (const BYTE *)wszNewData, dwSize);
	}

	RegCloseKey(hKey);
}

/*
 * Class:     com_ppsea_cocosjava_WindowsAdapter
 * Method:    setupView
 * Signature: (I)V
 */

LARGE_INTEGER nFreq;
LARGE_INTEGER nLast;
LARGE_INTEGER nNow;
HWND glWindow;
JNIEXPORT void JNICALL Java_com_ppsea_cocosjava_WindowsAdapter_setupView
  (JNIEnv * env, jclass clazz, jint hWnd)
{
	if(!s_delegate)
	{
		 s_delegate = new AppDelegate();
		 PVRFrameEnableControlWindow(false);


		 // Main message loop:


		 QueryPerformanceFrequency(&nFreq);
		 QueryPerformanceCounter(&nLast);

		 // Initialize instance and cocos2d.
		 if (!s_delegate->applicationDidFinishLaunching())
		 {
			 return ;
		 }

		 auto director = Director::getInstance();
		 auto glview = director->getOpenGLView();
		 glview->retain();
		 glWindow = glfwGetWin32Window(glview->getWindow());
	}

	
	HWND oldParent = SetParent(glWindow,(HWND)hWnd);
	
}
JNIEXPORT void JNICALL Java_com_ppsea_cocosjava_WindowsAdapter_setSearchPath
	(JNIEnv *env, jclass clazz, jstring path)
{
// 	vector<string> paths;
// 	paths.push_back(toNativeString(path));
// 	CCFileUtils::sharedFileUtils()->setSearchPaths(paths);
}
/*
 * Class:     com_ppsea_cocosjava_WindowsAdapter
 * Method:    resize
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_com_ppsea_cocosjava_WindowsAdapter_resize
	(JNIEnv * env, jclass clazz, jint width, jint height)
{
	//CCDirector::getInstance()->getOpenGLView()->setFrameSize(width,height);
	MoveWindow(glWindow,0,0,width,height,true);
}

/*
 * Class:     com_ppsea_cocosjava_WindowsAdapter
 * Method:    procMessage
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_com_ppsea_cocosjava_WindowsAdapter_procMessage
  (JNIEnv * env, jclass clazz, jint message, jint wParam, jint lParam)
{
	//CCDirector::getInstance()->getOpenGLView()->W
	//CCEGLView::sharedOpenGLView()->WindowProc(message,wParam,lParam);
}

/*
 * Class:     com_ppsea_cocosjava_WindowsAdapter
 * Method:    mainLoop
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_ppsea_cocosjava_WindowsAdapter_mainLoop
	(JNIEnv * env, jclass clazz)
{
	//CCLog("mapLoop...");
	QueryPerformanceCounter(&nNow);
	nLast.QuadPart = nNow.QuadPart;
	Director::getInstance()->mainLoop();
	Director::getInstance()->getOpenGLView()->pollEvents();
}

/*
 * Class:     com_ppsea_cocosjava_WindowsAdapter
 * Method:    distroyView
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_ppsea_cocosjava_WindowsAdapter_distroyView
  (JNIEnv * env, jclass clazz)
{

}


SUMMARY = "Arago TI SDK full filesystem image"

DESCRIPTION = "Complete Arago TI SDK filesystem image containing complete\
 applications and packages to entitle the SoC."

require arago-image.inc

ARAGO_DEFAULT_IMAGE_EXTRA_INSTALL ?= ""

# we're assuming some display manager is being installed with opengl
SYSTEMD_DEFAULT_TARGET = "${@bb.utils.contains('DISTRO_FEATURES','opengl','graphical.target','multi-user.target',d)}"

IMAGE_INSTALL += "\
    packagegroup-arago-base \
    packagegroup-arago-console \
    ti-test \
    ti-test-extras \
    ${@bb.utils.contains('DISTRO_FEATURES','opengl','packagegroup-arago-tisdk-graphics','',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','opengl','packagegroup-arago-tisdk-gtk','',d)} \
    ${@bb.utils.contains('DISTRO_FEATURES','opengl','packagegroup-arago-tisdk-qte','',d)} \
    ${@['','packagegroup-arago-tisdk-opencl'][oe.utils.all_distro_features(d, 'opencl', True, False) and bb.utils.contains('MACHINE_FEATURES', 'dsp', True, False, d)]} \
    packagegroup-arago-tisdk-connectivity \
    packagegroup-arago-tisdk-crypto \
    packagegroup-arago-tisdk-multimedia \
    packagegroup-arago-tisdk-addons \
    packagegroup-arago-tisdk-addons-extra \
    ${@bb.utils.contains('DISTRO_FEATURES','opengl','packagegroup-arago-tisdk-hmi','packagegroup-arago-base-tisdk-server-extra',d)} \
    ti-analytics \
    ti-demos \
    ${ARAGO_DEFAULT_IMAGE_EXTRA_INSTALL} \
    packagegroup-arago-tisdk-sysrepo \
	lvgl \
	lvgl-demo-fb \
"

export IMAGE_BASENAME = "tisdk-default-image${ARAGO_IMAGE_SUFFIX}"

# Disable ubi/ubifs as the filesystem requires more space than is
# available on the HW.
IMAGE_FSTYPES:remove:omapl138 = "ubifs ubi"

EXTRABROWSERS = " \
    qtwebbrowser-examples \
    qtwebengine-qmlplugins \
    qtwebengine-examples \
"

PYTHON2APPS = " \
    ${@bb.utils.contains('DISTRO_FEATURES','opengl',"${EXTRABROWSERS}",'',d)} \
"

DEVTOOLS = " \
    linux-libc-headers-dev \
    build-essential \
    packagegroup-core-tools-debug \
    git \
    dtc \
"

OPENCL = " \
    ${@bb.utils.contains('MACHINE_FEATURES','dsp','ti-opencl','',d)} \
    ${@bb.utils.contains('MACHINE_FEATURES','dsp','packagegroup-arago-tisdk-opencl-extra','',d)} \
"

IMAGE_INSTALL += "\
    ${@oe.utils.all_distro_features(d, "opencl", "${OPENCL}")} \
    ${@bb.utils.contains("BBFILE_COLLECTIONS", "meta-python2", "${PYTHON2APPS}", "", d)} \
    ${DEVTOOLS} \
    ${@bb.utils.contains('TUNE_FEATURES', 'armv7a', 'valgrind', '', d)} \
    docker \
"

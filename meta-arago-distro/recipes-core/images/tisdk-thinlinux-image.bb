SUMMARY = "Arago TI SDK Thin Linux image"

DESCRIPTION = "Minimal bootable image with container to start the next\
 complex system up."

require arago-image.inc

# Allow users to tack on additional packages as interesting.
ARAGO_THIN_IMAGE_EXTRA_INSTALL ?= ""

# we're assuming some display manager is being installed with opengl
SYSTEMD_DEFAULT_TARGET = "${@bb.utils.contains('DISTRO_FEATURES','opengl','graphical.target','multi-user.target',d)}"

IMAGE_INSTALL += "\
    packagegroup-arago-base \
    packagegroup-arago-console \
    ${@bb.utils.contains('DISTRO_FEATURES','opengl','packagegroup-arago-tisdk-graphics','',d)} \
    packagegroup-arago-tisdk-connectivity \
    packagegroup-arago-tisdk-crypto \
    docker \
    ${ARAGO_THIN_IMAGE_EXTRA_INSTALL} \
"

export IMAGE_BASENAME = "tisdk-thinlinux-image${ARAGO_IMAGE_SUFFIX}"

# Disable ubi/ubifs as the filesystem requires more space than is
# available on the HW.
IMAGE_FSTYPES:remove:omapl138 = "ubifs ubi"
